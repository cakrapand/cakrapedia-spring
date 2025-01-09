package com.example.userservice.user;

import com.example.userservice.user.dto.ResponseUserDto;
import com.example.userservice.utils.PasswordUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ResponseUserDto> getAllUsers(){
        return userRepository.findAll().stream().map(this::toResponseUserDto).toList();
    }

    public ResponseUserDto getUserById(String id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }

        return toResponseUserDto(user);
    }

    private ResponseUserDto toResponseUserDto(User user){
        return new ResponseUserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
