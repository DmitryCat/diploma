package com.ads_online.diploma.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdsDTO {
    @Schema(description = "количество объявлений")
    private Integer count;
    @Schema(description = "массив объявлений")
    private List<AdDTO> results;
}