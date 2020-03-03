package com.dongthanh.authservice.service.impl;

import com.dongthanh.authservice.domain.UserEntity;
import com.dongthanh.authservice.model.UserDTO;
import com.dongthanh.authservice.repository.UserRepository;
import com.dongthanh.authservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        UserEntity userEntity = userRepository.save(modelMapper.map(userDTO, UserEntity.class));
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public void delete(UserDTO userDTO) {
        userRepository.delete(modelMapper.map(userDTO, UserEntity.class));
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(userEntity -> modelMapper.map(userEntity, UserDTO.class)).collect(Collectors.toList());
    }
}
