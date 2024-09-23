package com.ads_online.diploma.service;

import com.ads_online.diploma.dto.UpdateUserDTO;
import com.ads_online.diploma.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    UserDTO getCurrentUser(Authentication authentication);
    UserDTO updateUser(UpdateUserDTO updateUser, Authentication authentication);
    void updateUserImage(MultipartFile image, String userName, Authentication authentication) throws IOException;

    UserDTO findByEmail(String login);
}
