package com.example.auth.Controller;

import com.example.auth.Entity.DTO.UserDTO;
import com.example.auth.Entity.Model.User;
import com.example.auth.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
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

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody UserDTO dto){
        if(userService.authUser(dto)) {
            return ResponseEntity.ok("User was successfully enter in service");
        }
        return ResponseEntity.ok("User not entered in service");
    }
}
