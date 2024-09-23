package com.ads_online.diploma.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

public interface ImageService {
    ResponseEntity<byte[]> getImage(Long id);

    Image addImage(MultipartFile image);
    void deleteImage(Long imageId);
    void saveImage(Image image);
}