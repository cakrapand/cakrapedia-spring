package com.example.productservice.product;


import com.example.productservice.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        BaseResponse response = new BaseResponse(true, "OK", products);
        return ResponseEntity.ok(response);
    }
}
