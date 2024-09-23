package com.ads_online.diploma.utils;
import com.ads_online.diploma.exeptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CheckAuthentication {
    public void checkAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            log.error("Пользователь не авторизован");
            throw new UnauthorizedException("Пользователь не авторизован");
        }
    }
}