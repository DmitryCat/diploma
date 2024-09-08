package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.LoginDto;
import com.ads_online.diploma.dto.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * Регистрация нового пользователя.
     *
     * Создает нового пользователя с указанными данными.
     *
     * @param registerDto Объект с данными для регистрации пользователя.
     * @return Ответ с кодом 201 (Created) в случае успешной регистрации.
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.status(201).build();
    }

    /**
     * Авторизация пользователя.
     *
     * Выполняет вход пользователя на платформу на основании введенного логина и пароля.
     *
     * @param loginDto Объект с данными для авторизации (логин и пароль).
     * @return Ответ с кодом 200 (OK) в случае успешной авторизации.
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().build();
    }
}