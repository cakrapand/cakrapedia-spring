package com.example.cartservice.cart;

import com.example.cartservice.BaseResponse;
import com.example.cartservice.cart.dto.ResponseCartDto;
import com.example.cartservice.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public CartService(CartRepository cartRepository, ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.productClient = productClient;
    }

    public List<ResponseCartDto> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(this::mapToResponseCartDto).toList();
    }

    public ResponseCartDto getCartById(String id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
        return mapToResponseCartDto(cart);
    }

    public ResponseCartDto editCartById(String id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
        cart.setId("");
        Cart newCart = cartRepository.save(cart);
        return mapToResponseCartDto(newCart);
    }

    public String helloFromCart(){
        ResponseEntity<BaseResponse<String>> response = productClient.helloFromProduct();
        if(response.getBody() == null){
            throw new RuntimeException();
        }
        return response.getBody().getData();
    }

    private ResponseCartDto mapToResponseCartDto(Cart cart) {
        return ResponseCartDto
                .builder()
                .id(cart.getId())
                .build();
    }
}
