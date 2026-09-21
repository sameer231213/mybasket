package com.mybasket.app.controller;

import com.mybasket.app.dto.PageResponse;
import com.mybasket.app.dto.ProductDto;
import com.mybasket.app.entity.FileMetaData;
import com.mybasket.app.entity.Product;
import com.mybasket.app.service.FileStorageService;
import com.mybasket.app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public PageResponse<Product> getProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        return productService.getAll(page, size, sortBy, sortDir);
    }

    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId) {
        return productService.get(productId);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productdto) {
        ProductDto savedEntity = productService.createProduct(productdto);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody Product product
    ) {
        Product savedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{productId}/image")
    public ResponseEntity<FileMetaData> uploadProductImage(
            @PathVariable("productId") Long productId,
            @RequestParam("productImage") MultipartFile file
    ) throws IOException {

        String contentType = file.getContentType();

        if (contentType == null ||
                !(contentType.equalsIgnoreCase("image/png") ||
                        contentType.equalsIgnoreCase("image/jpeg"))) {
            throw new BadRequestException("Invalid file type");
        }

        Product product = productService.get(productId);

        FileMetaData fileMetaData = fileStorageService.uploadFile(file);
        product.setImage(fileMetaData);

        productService.updateProduct(productId, product);

        return ResponseEntity.ok(fileMetaData);
    }

    @GetMapping("/{productId}/image")
    public ResponseEntity<Resource> serveFile(
            @PathVariable("productId") Long productId
    ) throws MalformedURLException, BadRequestException {

        Product product = productService.get(productId);
        FileMetaData fileMetaData = product.getImage();

        if (fileMetaData == null) {
            throw new BadRequestException("Image not found for this product");
        }

        Resource resource = fileStorageService.loadFile(fileMetaData);

        String contentType = fileMetaData.getFileType();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\""
                )
                .body(resource);
    }
}