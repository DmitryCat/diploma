package com.ads_online.diploma.service;

import com.ads_online.diploma.dto.AdDTO;
import com.ads_online.diploma.dto.AdsDTO;
import com.ads_online.diploma.dto.CreateOrUpdateAdDTO;
import com.ads_online.diploma.dto.ExtendedAdDTO;
import com.ads_online.diploma.entity.Ad;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;


public interface AdService {
    void uploadImageForAd(Integer id, MultipartFile image) throws IOException;
    AdDTO getAllAds();
    AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image,
                Authentication authentication) throws IOException;
    ExtendedAdDTO getById(Integer id, Authentication authentication);
    void deleteAd(Integer id, Authentication authentication);
    boolean isAdCreatorOrAdmin(AdDTO ad, Authentication authentication);

    AdDTO updateAd(Integer id, CreateOrUpdateAdDTO createOrUpdateAdDTO,
                   Authentication authentication);

    AdsDTO getAdsMe(Authentication authentication);
    void updateImage(Integer id, MultipartFile image,
                     Authentication authentication) throws IOException;
    Optional<Ad> findById(Long id);
    void checkAdIsPresent(Optional<AdDTO> ad);
}