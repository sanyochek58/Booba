package com.example.auth.Controller;

import com.example.auth.Entity.DTO.UserDTO;
import com.example.auth.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController{
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto){
        userService.registerUser(dto);
        return ResponseEntity.ok("User was registered successfully");
    }
}
