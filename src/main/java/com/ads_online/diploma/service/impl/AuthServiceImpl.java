package com.ads_online.diploma.service.impl;

import com.ads_online.diploma.dto.AuthResponseDto;
import com.ads_online.diploma.dto.LoginDto;
import com.ads_online.diploma.dto.RegisterDto;
import com.ads_online.diploma.dto.UserDto;
import com.ads_online.diploma.service.AuthService;
import org.springframework.stereotype.Service;

@Service

public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        return null;
    }

    @Override
    public UserDto register(RegisterDto registerDTO) {
        return null;
    }
}
