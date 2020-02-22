package com.dongthanh.authservice.controller;

import com.dongthanh.authservice.domain.UserEntity;
import com.dongthanh.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<String> getUser(@RequestParam("username") String username){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        return ResponseEntity.ok(optionalUserEntity.get().getUsername());
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestParam("username") String username,
                                   @RequestParam("password") String password){
        UserEntity user = new UserEntity(username, password, true);
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
