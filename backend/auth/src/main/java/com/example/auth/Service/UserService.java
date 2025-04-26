package com.example.auth.Service;

import com.example.auth.Entity.DTO.UserDTO;
import com.example.auth.Entity.Model.User;

public interface UserService {
    User registerUser(UserDTO dto);
    String authUser(UserDTO dto);
}
