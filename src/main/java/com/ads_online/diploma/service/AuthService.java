package com.ads_online.diploma.service;

import com.ads_online.diploma.dto.RegisterDTO;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterDTO registerDTO);
}