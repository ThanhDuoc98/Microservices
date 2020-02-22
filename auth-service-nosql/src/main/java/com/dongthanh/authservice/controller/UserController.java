package com.dongthanh.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(path = "/demo")
    public ResponseEntity<String> getDemo(){
        return ResponseEntity.ok("Dong Thanh");
    }
}
