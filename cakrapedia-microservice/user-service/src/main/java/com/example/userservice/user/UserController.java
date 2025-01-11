package com.example.userservice.user;


import com.example.userservice.BaseResponse;
import com.example.userservice.user.dto.ResponseUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<ResponseUserDto>>> getUsers() {
        List<ResponseUserDto> users = userService.getAllUsers();
        BaseResponse<List<ResponseUserDto>> response = BaseResponse.<List<ResponseUserDto>>builder().success(true).message("OK").data(users).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ResponseUserDto>> getUsersById(@PathVariable String id) {
        ResponseUserDto user = userService.getUserById(id);
        BaseResponse<ResponseUserDto> response = BaseResponse.<ResponseUserDto>builder().success(true).message("OK").data(user).build();
        return ResponseEntity.ok(response);
    }

}


// 1SJPWCfIC46ZPVhg
