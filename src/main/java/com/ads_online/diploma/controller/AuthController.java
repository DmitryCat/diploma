package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.AuthResponseDto;
import com.ads_online.diploma.dto.LoginDto;
import com.ads_online.diploma.dto.RegisterDto;
import com.ads_online.diploma.dto.UserDto;
import com.ads_online.diploma.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Авторизация и аутентификация")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Авторизация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Авторизация успешна"),
            @ApiResponse(responseCode = "401", description = "Неверные учетные данные")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        AuthResponseDto authResponse = authService.login(loginDto);
        return ResponseEntity.ok(authResponse);
    }
    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Регистрация успешна"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto registerDTO) {
        UserDto registeredUser = authService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}