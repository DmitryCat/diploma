package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Получение информации об авторизованном пользователе.
     * @return Объект UserDto, представляющий информацию о пользователе.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        UserDto userDto = new UserDto();
        return ResponseEntity.ok(userDto);
    }

    /**
     * Обновление информации об авторизованном пользователе.
     * @param updateUserDto Объект с новыми данными пользователя.
     * @return Обновленный объект UpdateUserDto.
     */
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(updateUserDto);
    }

    /**
     * Обновление аватара авторизованного пользователя.
     * @param image Файл изображения в виде строки.
     * @return Пустой ответ со статусом OK.
     */
    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") String image) {
        return ResponseEntity.ok().build();
    }

    /**
     * Обновление пароля пользователя.
     * @param newPasswordDto Объект с текущим и новым паролем.
     * @return Пустой ответ со статусом OK.
     */
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }
}