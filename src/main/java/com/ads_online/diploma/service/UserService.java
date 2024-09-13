package com.ads_online.diploma.service;

import com.ads_online.diploma.dto.RegisterDto;
import com.ads_online.diploma.dto.UserDto;
import com.ads_online.diploma.model.User;
import com.ads_online.diploma.model.UserRole;
import com.ads_online.diploma.repository.UserRepository;
    import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto register(RegisterDto registerDto) {
        User user = modelMapper.map(registerDto, User.class);
        user.setRole(UserRole.USER); // Присваиваем роль по умолчанию
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }
}