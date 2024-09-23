package com.ads_online.diploma.utils;

import com.ads_online.diploma.entity.Role;
import com.ads_online.diploma.entity.User;
import com.ads_online.diploma.repository.UserRepository;
import org.springframework.stereotype.Component;
@Component
public class CheckAdmin {

    private final UserRepository repository;

    public CheckAdmin(UserRepository repository) {
        this.repository = repository;
    }

    public boolean isAdmin(String email) {
        User user = repository.findByEmail(email);
        return user != null && user.getRole().equals(Role.ADMIN);
    }
}