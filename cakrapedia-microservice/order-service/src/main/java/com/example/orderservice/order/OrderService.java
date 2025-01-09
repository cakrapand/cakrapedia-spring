package com.example.orderservice.order;

import com.example.orderservice.order.dto.ResponseOrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ResponseOrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToResponseOrderDto).toList();
    }

    public ResponseOrderDto getOrderById(String id) {
        Order product = orderRepository.findById(id).orElse(null);
        if (product == null) {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");}
        return mapToResponseOrderDto(product);
    }

    private ResponseOrderDto mapToResponseOrderDto(Order order) {
        return ResponseOrderDto
                .builder()
                .build();
    }
}
