package com.dongthanh.authservice.service.impl;

import com.dongthanh.authservice.domain.UserEntity;
import com.dongthanh.authservice.model.UserDTO;
import com.dongthanh.authservice.repository.UserRepository;
import com.dongthanh.authservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = userRepository.save(modelMapper.map(userDTO, UserEntity.class));
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
