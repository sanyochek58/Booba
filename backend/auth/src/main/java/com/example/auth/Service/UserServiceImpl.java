package com.example.auth.Service;

import com.example.auth.Entity.DTO.UserDTO;
import com.example.auth.Entity.Model.User;
import com.example.auth.Repository.UserRepository;
import com.example.auth.Security.JwtTokenUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtTokenUtil jwtTokenUtil;

    public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil){
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
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
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUsername(dto.getUsername());

        user.setBirthOfDate(dto.getBirthOfDate());

        return userRepository.save(user);
    }
    @Override
    @Transactional
    public String authUser(UserDTO dto){
        Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());
        boolean isAuth = true;
        if(userOpt.isEmpty()){
            isAuth = false;
            throw new RuntimeException("Пользователь не существует !");
        }

        User user = userOpt.get();
        if(passwordEncoder.matches(dto.getPassword(),user.getUsername())){
            isAuth = false;
            throw new RuntimeException("Неверный пароль!");
        }
        return jwtTokenUtil.generateToken(user.getUsername());
    }
}
