package com.ads_online.diploma.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdDTO {
    @Schema(description = "id объявления")
    private Long pk;

    @Schema(description = "id автора объявления")
    private Integer author;

    @Schema(description = "ссылка на картинку объявления")
    private String image;

    @Schema(description = "цена объявления")
    private Integer price;

    @Schema(description = "заголовок объявления")
    private String title;
}