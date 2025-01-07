package com.example.productservice.product;

import com.example.productservice.product.dto.ResponseProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpClient;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ResponseProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToResponseProductDto).toList();
    }

    public ResponseProductDto getProductById(String id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");}
        return mapToResponseProductDto(product);
    }

    private ResponseProductDto mapToResponseProductDto(Product product) {
        return ResponseProductDto
                .builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
