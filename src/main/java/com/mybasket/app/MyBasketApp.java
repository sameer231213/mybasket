package com.mybasket.app;

import com.mybasket.app.entity.Product;
import com.mybasket.app.entity.User;
import com.mybasket.app.repository.CategoryRepository;
import com.mybasket.app.repository.ProductRepository;
import com.mybasket.app.repository.UserRepository;
import com.mybasket.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyBasketApp implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyBasketApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App started");

        User user = new User();
        user.setName("Sameer");
        user.setEmail("sameer" + System.currentTimeMillis() + "@example.in");

        userService.saveUser(user);

        int userId = user.getUserId();
        System.out.println("User saved with ID: " + userId);

        Product product = new Product();
        product.setTitle("Iphone 17 pro max");
        product.setDescription("This is new iPhone");
        product.setShort_description("This is iPhone");
        product.setLive(true);
        product.setPrice(80000);
        product.setOutofstock(false);

        productRepository.save(product);

        productRepository
                .findByProductIdAndTitle(product.getProductId(), product.getTitle())
                .ifPresentOrElse(
                        foundProduct -> System.out.println(
                                foundProduct.getTitle() + ": "
                                        + foundProduct.getDescription() + " : "
                                        + foundProduct.getPrice()
                        ),
                        () -> System.out.println("Product not found")
                );


//        User user = new User();
//        user.setName("Sameer");
//        user.setEmail("@sameer");
//        user.setPassword("@123");
//   user.setUserImageUrl("abc.png");
//   userRepository.save(user);


//        userRepository.findById(123).ifPresentOrElse(
//                user1 -> {
//                    userRepository.delete(user1);
//
//                },
//                () -> {
//                    System.out.println("User is already deleted");
//                });
//    }
    }
}