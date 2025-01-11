package com.example.orderservice.order;


import com.example.orderservice.BaseResponse;
import com.example.orderservice.order.dto.ResponseOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<ResponseOrderDto>>> getAllOrders() {
        List<ResponseOrderDto> products = orderService.getAllOrders();
        BaseResponse<List<ResponseOrderDto>> response = BaseResponse.<List<ResponseOrderDto>>builder().success(true).message("OK").data(products).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ResponseOrderDto>> getOrderById(@PathVariable("id") String id) {
        ResponseOrderDto product = orderService.getOrderById(id);
        BaseResponse<ResponseOrderDto> response = BaseResponse.<ResponseOrderDto>builder().success(true).message("OK").data(product).build();
        return ResponseEntity.ok(response);
    }
}
