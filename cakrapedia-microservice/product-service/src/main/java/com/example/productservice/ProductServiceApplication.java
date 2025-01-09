package com.example.productservice;

import com.example.productservice.product.Product;
import com.example.productservice.product.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) { SpringApplication.run(ProductServiceApplication.class, args); }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            int max = 10;
            Faker faker = new Faker(new Locale("en-US"));

            for(int i = 0; i < max; i++)
            {
                Product product = new Product();
                product.setName(faker.commerce().productName());
                product.setPrice(10000L);
                product.setStock(faker.number().numberBetween(1, 50));
                productRepository.save(product);
            }
        };
    }

}
