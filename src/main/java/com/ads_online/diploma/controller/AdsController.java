package com.ads_online.diploma.controller;

import com.ads_online.diploma.service.AdsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import com.ads_online.diploma.dto.AdDto;

@RestController
@RequestMapping("/ads")
@Tag(name = "Ads", description = "Управление объявлениями")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {


    private final AdsService adsService;


    @Operation(summary = "Получение всех объявлений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список объявлений успешно получен")
    })
    @GetMapping
    public ResponseEntity<List<AdDto>> getAllAds() {
        List<AdDto> ads = adsService.getAllAds();
        return ResponseEntity.ok(ads);
    }

    @Operation(summary = "Создание нового объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Объявление успешно создано"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping
    public ResponseEntity<AdDto> createAd(@RequestBody AdDto adDto) {
        AdDto createdAd = adsService.createAd(adDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAd);
    }
}