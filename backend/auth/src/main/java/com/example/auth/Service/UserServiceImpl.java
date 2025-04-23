package com.example.auth.Service;

import com.example.auth.Entity.DTO.UserDTO;
import com.example.auth.Entity.Model.User;
import com.example.auth.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User registerUser(UserDTO dto){
        Optional<User> usernames = userRepository.findByUsername(dto.getUsername());
        if(usernames.isPresent()){
            throw new RuntimeException("Имя уже занято !");
        }

        Optional<User> emails = userRepository.findByEmail(dto.getEmail());
        if(emails.isPresent()){
            throw new RuntimeException("Email уже используется !");
        }



        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());



        user.setBirthOfDate(dto.getBirthOfDate());

        return userRepository.save(user);
    }
}
