package com.dongthanh.authservice.controller;

import com.dongthanh.authservice.model.UserDTO;
import com.dongthanh.authservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<UserDTO>> getAll(Pageable pageable){
        try{
            Page<UserDTO> users = new PageImpl<>(userService.findAll(pageable));
            LOG.info("Get all users successfully!");
            return new ResponseEntity(users, HttpStatus.OK);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("userId") String userId){
        try{
            UserDTO userDTO = new UserDTO(userId);
            userService.delete(userDTO);
            LOG.info("Delete user : " + userId + " successfully!");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity save(@RequestBody UserDTO userDTO){
        try{
            userService.save(userDTO);
            LOG.info("Create user : " + userDTO.getUsername() + " successfully!");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
