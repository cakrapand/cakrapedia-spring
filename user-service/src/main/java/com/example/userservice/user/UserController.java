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
    public ResponseEntity<BaseResponse> getUsers() {
        List<ResponseUserDto> users = userService.getAllUsers();
        BaseResponse body = new BaseResponse(true, "OK", users);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getUsersById(@PathVariable String id) {
        ResponseUserDto user = userService.getUserById(id);
        BaseResponse body = new BaseResponse(true, "OK", user);
        return ResponseEntity.ok(body);
    }

}


// 1SJPWCfIC46ZPVhg
