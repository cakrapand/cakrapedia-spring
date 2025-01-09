package com.example.productservice.product;


import com.example.productservice.BaseResponse;
import com.example.productservice.product.dto.ResponseProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<ResponseProductDto> products = productService.getAllProducts();
        BaseResponse response = BaseResponse.builder().success(true).message("OK").data(products).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable("id") String id) {
        ResponseProductDto product = productService.getProductById(id);
        BaseResponse response = BaseResponse.builder().success(true).message("OK").data(product).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public ResponseEntity<BaseResponse> hello() {
        BaseResponse response = BaseResponse.builder().success(true).message("OK").data("Hello from product service").build();
        return ResponseEntity.ok(response);
    }
}
