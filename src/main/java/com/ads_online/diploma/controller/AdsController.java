package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        // Заглушка
        return ResponseEntity.ok(new AdsDto());
    }

    @PostMapping
    public ResponseEntity<AdDto> addAd() {
        // Заглушка
        return ResponseEntity.status(201).body(new AdDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id) {
        // Заглушка
        return ResponseEntity.ok(new ExtendedAdDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        // Заглушка
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto adDto) {
        // Заглушка
        return ResponseEntity.ok(new AdDto());
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        // Заглушка
        return ResponseEntity.ok(new AdsDto());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer id) {
        // Заглушка
        return ResponseEntity.ok().build();
    }
}