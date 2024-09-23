package com.ads_online.diploma.utils;

import com.ads_online.diploma.entity.User;
import com.ads_online.diploma.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdUtils {
    private final Logger logger = LoggerFactory.getLogger(AdUtils.class);

    private final UserRepository userRepository;

    public User handleUser(Authentication authentication) {
        logger.info("Was invoked handle User method");
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(principal.getUsername());
    }
}
