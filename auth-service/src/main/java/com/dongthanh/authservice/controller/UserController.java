package com.dongthanh.authservice.controller;

import com.dongthanh.authservice.domain.RoleEntity;
import com.dongthanh.authservice.domain.UserEntity;
import com.dongthanh.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();
        return ResponseEntity.ok(userEntities);
    }

    @GetMapping(path = "/user")
    public ResponseEntity<Object> getUser(@RequestParam("username") String username){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        return ResponseEntity.ok(optionalUserEntity.get());
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestParam("username") String username,
                                   @RequestParam("password") String password){
        UserEntity user = new UserEntity(username, password, true);
        RoleEntity role = new RoleEntity("ROLE_ADMIN", "SYSTEM ADMIN");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
