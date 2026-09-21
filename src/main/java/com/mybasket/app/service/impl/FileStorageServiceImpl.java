package com.mybasket.app.service.impl;

import com.mybasket.app.entity.FileMetaData;
import com.mybasket.app.repository.FileMetaDataRepo;
import com.mybasket.app.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${app.files.upload.folder.products}")
    private String productUploadFolder;

    private final FileMetaDataRepo fileMetaDataRepo;

    @Override
    public FileMetaData uploadFile(MultipartFile file) throws IOException {

        String storeName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path uploadPath = Files.createDirectories(Paths.get(productUploadFolder));
        Path filePath = uploadPath.resolve(storeName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        FileMetaData fileMetaData = new FileMetaData();

        fileMetaData.setFileSize(file.getSize());
        fileMetaData.setOriginalName(file.getOriginalFilename());
        fileMetaData.setStoredName(storeName);
        fileMetaData.setFileType(file.getContentType());
        fileMetaData.setFileUrl(filePath.toString());
        fileMetaData.setStorageType("local");

        return fileMetaDataRepo.save(fileMetaData);
    }

    @Override
    public Resource loadFile(FileMetaData fileMetaData)
            throws MalformedURLException, BadRequestException {

        if (fileMetaData == null || fileMetaData.getStoredName() == null) {
            throw new BadRequestException("File does not exist");
        }

        Path path = Paths.get(productUploadFolder)
                .resolve(fileMetaData.getStoredName())
                .normalize();

        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            throw new BadRequestException("File not found !!");
        }

        return resource;
    }
}