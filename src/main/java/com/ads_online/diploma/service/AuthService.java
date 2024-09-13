package com.ads_online.diploma.service;

import com.ads_online.diploma.dto.AuthResponseDto;
import com.ads_online.diploma.dto.LoginDto;
import com.ads_online.diploma.dto.RegisterDto;
import com.ads_online.diploma.dto.UserDto;

public interface AuthService {
    AuthResponseDto login(LoginDto loginDto);
    UserDto register(RegisterDto registerDTO);
}
