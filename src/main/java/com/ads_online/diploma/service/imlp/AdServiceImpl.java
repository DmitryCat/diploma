package com.ads_online.diploma.service.imlp;

import com.ads_online.diploma.dto.AdDTO;
import com.ads_online.diploma.dto.AdsDTO;
import com.ads_online.diploma.dto.CreateOrUpdateAdDTO;
import com.ads_online.diploma.dto.ExtendedAdDTO;
import com.ads_online.diploma.entity.Ad;
import com.ads_online.diploma.entity.Image;
import com.ads_online.diploma.entity.Role;
import com.ads_online.diploma.exeptions.AccessRightsNotAvailableException;
import com.ads_online.diploma.exeptions.AdNotFoundException;
import com.ads_online.diploma.mapper.AdMapper;
import com.ads_online.diploma.repository.AdRepository;
import com.ads_online.diploma.service.AdService;
import com.ads_online.diploma.service.CommentService;
import com.ads_online.diploma.service.ImageService;
import com.ads_online.diploma.service.UserService;
import com.ads_online.diploma.utils.CheckAdmin;
import com.ads_online.diploma.utils.CheckAuthentication;
import com.ads_online.diploma.utils.MethodLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final CommentService commentService;
    private final CheckAuthentication checkAuthentication;
    private final CheckAdmin checkAdmin;

    @Value("${path.to.photo.folder}")
    private String photoDir;

    @Override
    public AdsDTO getAllAds() {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());
        List<AdDTO> listOfAds = adRepository.findAll().stream()
                .map(AdMapper.INSTANCE::adToAdDTO).collect(Collectors.toList());
        return new AdsDTO(listOfAds.size(), listOfAds);
    }

    @Override
    @Transactional
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image,
                       Authentication authentication) throws IOException {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        checkAuthentication.checkAuthentication(authentication);
        checkAdmin.isAdmin(authentication.getName());

        Ad ad = AdMapper.INSTANCE.createOrUpdateAdDTOToAd(createOrUpdateAdDTO);
        ad.setUser(userService.findByEmail(authentication.getName()));
        ad.setImage(imageService.addImage(image));
        adRepository.save(ad);
        log.info("Объявление сохранено: {}", ad);

        uploadImageForAd(ad.getPk().intValue(), image);
        return AdMapper.INSTANCE.adToAdDTO(ad);
    }

    @Override
    public ExtendedAdDTO getById(Integer id, Authentication authentication) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        checkAuthentication.checkAuthentication(authentication);
        checkAdmin.isAdmin(authentication.getName());

        Optional<Ad> ad = findById(Long.valueOf(id));
        checkAdIsPresent(ad);

        return ad.map(AdMapper.INSTANCE::toExtendedAdDTO).orElse(null);
    }

    @Override
    public void deleteAd(Integer id, Authentication authentication) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        checkAuthentication.checkAuthentication(authentication);

        Optional<Ad> foundAd = findById(Long.valueOf(id));
        checkAdIsPresent(foundAd);

        Ad ad = foundAd.get();

        if (isAdCreatorOrAdmin(ad, authentication)) {
            adRepository.getReferenceById(Long.valueOf(id))
                    .getComments().forEach(comment -> commentService.deleteAll());
            log.info("Комментарии удалены");

            imageService.deleteImage(ad.getImage().getId());
            log.info("Изображение удалено");

            adRepository.deleteById(id.longValue());
            log.info("Объявление удалено");
        } else {
            log.error("Отсутствует доступ к объявлению");
            throw new AccessRightsNotAvailableException("Отсутствует доступ к объявлению");
        }
    }

    @Override
    public boolean isAdCreatorOrAdmin(Ad ad, Authentication authentication) {
        return userService.findByEmail(authentication.getName()).getRole() == Role.ADMIN
                || authentication.getName().equals(ad.getUser().getEmail());
    }

    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAdDTO createOrUpdateAdDTO,
                          Authentication authentication) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());
        checkAuthentication.checkAuthentication(authentication);

        Optional<Ad> foundAd = findById(id.longValue());
        checkAdIsPresent(foundAd);

        Ad ad = foundAd.get();

        if (isAdCreatorOrAdmin(ad, authentication)) {
            ad.setPrice(createOrUpdateAdDTO.getPrice());
            ad.setTitle(createOrUpdateAdDTO.getTitle());
            ad.setDescription(createOrUpdateAdDTO.getDescription());

            adRepository.save(ad);
            log.info("Объявление сохранено: {}", ad);
            return AdMapper.INSTANCE.adToAdDTO(ad);
        } else {
            log.error("Отсутствует доступ к объявлению");
            throw new AccessRightsNotAvailableException("Отсутствует доступ к объявлению");
        }
    }

    @Override
    public AdsDTO getAdsMe(Authentication authentication) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        checkAuthentication.checkAuthentication(authentication);
        checkAdmin.isAdmin(authentication.getName());

        List<AdDTO> listOfAds = adRepository.findAll().stream()
                .filter(ad -> (ad.getUser().getEmail()).equals(authentication.getName()))
                .map(AdMapper.INSTANCE::adToAdDTO).collect(Collectors.toList());
        return new AdsDTO(listOfAds.size(), listOfAds);
    }

    @Override
    public void updateImage(Integer id, MultipartFile image,
                            Authentication authentication) throws IOException {
        checkAuthentication.checkAuthentication(authentication);

        Optional<Ad> foundAd = findById(id.longValue());
        checkAdIsPresent(foundAd);
        Ad ad = foundAd.get();
        if (isAdCreatorOrAdmin(ad, authentication)) {
            uploadImageForAd(id, image);
        } else {
            log.error("Отсутствует доступ к объявлению");
            throw new AccessRightsNotAvailableException("Отсутствует доступ к объявлению");
        }
    }

    @Override
    public Optional<Ad> findById(Long id) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());
        return adRepository.findById(id);
    }

    @Override
    public void uploadImageForAd(Integer id, MultipartFile image) throws IOException {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Ad ad = findById(id.longValue()).get();
        Path filePath = Path.of(photoDir, ad.getPk() + "." + getExtension(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        Image image1 = Optional.ofNullable(ad.getImage())
                .orElse(new Image());

        image1.setFilePath(filePath.toString());
        image1.setFileSize(image.getSize());
        image1.setMediaType(image.getContentType());
        image1.setData(image.getBytes());
        imageService.saveImage(image1);
        log.info("Изображение сохранено: {}", image1);
        ad.setImage(image1);
        adRepository.save(ad);
        log.info("Изображение объявления установлено: {}", ad);
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    @Override
    public void checkAdIsPresent(Optional<Ad> ad) {
        if (!ad.isPresent()) {
            log.error("Объявление не найдено");
            throw new AdNotFoundException("Объявление не найдено");
        }
    }
}