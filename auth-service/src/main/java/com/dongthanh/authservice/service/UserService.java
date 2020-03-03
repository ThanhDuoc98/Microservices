package com.dongthanh.authservice.service;

import com.dongthanh.authservice.model.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    List<UserDTO> findAll(Pageable pageable);

    void delete(UserDTO userDTO);
}
