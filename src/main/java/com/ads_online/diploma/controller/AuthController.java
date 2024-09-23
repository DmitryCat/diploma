package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.LoginDTO;
import com.ads_online.diploma.dto.RegisterDTO;
import com.ads_online.diploma.service.AuthService;
import com.ads_online.diploma.utils.MethodLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Tag(name = "Авторизация")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        log.info("Использован метод {}", MethodLog.getMethodName());

        if (authService.login(loginDTO.getUsername(), loginDTO.getPassword())) {
            log.info("Авторизация прошла успешно");
            return ResponseEntity.ok().build();
        } else {
            log.error("Неверные имя пользователя или пароль");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя", responses = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST")
    })
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        log.info("Использован метод {}", MethodLog.getMethodName());

        if (authService.register(registerDTO)) {
            log.info("Регистрация прошла успешно");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            log.error("Пользователь с таким именем уже существует");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}