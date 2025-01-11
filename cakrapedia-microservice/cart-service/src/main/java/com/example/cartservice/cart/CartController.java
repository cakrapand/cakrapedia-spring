package com.example.cartservice.cart;


import com.example.cartservice.BaseResponse;
import com.example.cartservice.cart.dto.ResponseCartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<ResponseCartDto>>> getAllCarts() {
        List<ResponseCartDto> carts = cartService.getAllCarts();
        BaseResponse<List<ResponseCartDto>> response = BaseResponse.<List<ResponseCartDto>>builder().success(true).message("OK").data(carts).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ResponseCartDto>> getCartById(@PathVariable("id") String id) {
        ResponseCartDto cart = cartService.getCartById(id);
        BaseResponse<ResponseCartDto> response = BaseResponse.<ResponseCartDto>builder().success(true).message("OK").data(cart).build();
        return ResponseEntity.ok(response);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<BaseResponse> editCartById(@PathVariable("id") String id) {
//        ResponseCartDto cart = cartService.editCartById(id);
//        BaseResponse response = BaseResponse.builder().success(true).message("OK").data(cart).build();
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/helloFromCart")
    public ResponseEntity<BaseResponse<String>> hello() {
        String hello = cartService.helloFromCart();
        BaseResponse<String> response = BaseResponse.<String>builder().success(true).message("OK").data(hello).build();
        return ResponseEntity.ok(response);
    }
}
