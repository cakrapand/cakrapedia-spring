package com.example.cartservice.client;

import com.example.cartservice.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "product", url = "http://localhost:8081")
public interface ProductClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/v1/products/helloFromProduct")
    ResponseEntity<BaseResponse<String>> helloFromProduct();
}
