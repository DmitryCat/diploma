package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        // Заглушка
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        // Заглушка
        return ResponseEntity.ok(new UserDto());
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        // Заглушка
        return ResponseEntity.ok(updateUserDto);
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage() {
        // Заглушка
        return ResponseEntity.ok().build();
    }
}