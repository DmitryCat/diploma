package com.ads_online.diploma.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
public class CreateOrUpdateCommentDTO {
    @Schema(description = "Текст комментария", minLength = 8, maxLength = 64)
    @Length(min = 8, max = 64)
    private String text;
}