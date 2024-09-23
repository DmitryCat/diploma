package com.ads_online.diploma.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDTO {
    @Schema(description = "Имя пользователя")
    @Size(min = 8, max = 16)
    private String username;

    @Schema(description = "Пароль")
    @Size(min = 4, max = 32)
    private String password;
}