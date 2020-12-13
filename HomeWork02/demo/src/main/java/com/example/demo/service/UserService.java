package com.example.demo.service;

import com.example.demo.entities.UserEntity;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String saveUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserName(user.getUserName());
        UserEntity userEntity1 = userRepository.save(userEntity);
        return userEntity1.getUserName();
    }
}