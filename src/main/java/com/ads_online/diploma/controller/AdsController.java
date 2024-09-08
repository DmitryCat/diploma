package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.AdDto;
import com.ads_online.diploma.dto.AdsDto;
import com.ads_online.diploma.dto.CreateOrUpdateAdDto;
import com.ads_online.diploma.dto.ExtendedAdDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ads")
public class AdsController {

    /**
     * Получение списка всех объявлений.
     *
     * Возвращает список всех объявлений, которые доступны на платформе.
     *
     * @return Список объектов AdDto.
     */
    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto adsDto = new AdsDto();
        return ResponseEntity.ok(adsDto);
    }

    /**
     * Создание нового объявления.
     *
     * Добавляет новое объявление с указанными параметрами.
     *
     * @param createOrUpdateAdDto Объект с данными для создания объявления.
     * @return Объект AdDto с информацией о созданном объявлении.
     */
    @PostMapping
    public ResponseEntity<AdDto> addAd(@RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        AdDto adDto = new AdDto();
        return ResponseEntity.status(201).body(adDto);
    }

    /**
     * Получение информации об объявлении по его ID.
     *
     * Возвращает детальную информацию об объявлении с указанным ID.
     *
     * @param id Идентификатор объявления.
     * @return Объект ExtendedAdDto с подробной информацией об объявлении.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAd(@PathVariable("id") Integer id) {
        ExtendedAdDto extendedAdDto = new ExtendedAdDto();
        return ResponseEntity.ok(extendedAdDto);
    }

    /**
     * Удаление объявления по его ID.
     *
     * Удаляет объявление с указанным ID.
     *
     * @param id Идентификатор объявления.
     * @return Ответ с кодом 204 (No Content) в случае успешного удаления.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Integer id) {
        return ResponseEntity.noContent().build();
    }

    /**
     * Обновление информации об объявлении.
     *
     * Обновляет информацию о конкретном объявлении по его ID.
     *
     * @param id Идентификатор объявления.
     * @param createOrUpdateAdDto Объект с обновленными данными объявления.
     * @return Обновленный объект AdDto с новой информацией.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        AdDto adDto = new AdDto();
        return ResponseEntity.ok(adDto);
    }
}