package com.ads_online.diploma.service;

import com.ads_online.diploma.dto.AdDto;

import java.util.List;

public interface AdsService {
    List<AdDto> getAllAds();

    AdDto createAd(AdDto adDto);
}
