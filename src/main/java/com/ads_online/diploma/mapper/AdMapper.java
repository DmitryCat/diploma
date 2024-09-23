package com.ads_online.diploma.mapper;

import com.ads_online.diploma.dto.CreateOrUpdateAdDTO;
import com.ads_online.diploma.dto.ExtendedAdDTO;
import com.ads_online.diploma.entity.Ad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(target = "user", source = "ad.user.id")
    @Mapping(target = "pk", source = "ad.pk")
    @Mapping(target = "image", expression = "java(\"/image/\" + ad.getImage().getId())")
    Ad adToAd(Ad ad);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "image", ignore = true)
    Ad createOrUpdateAdDTOToAd(CreateOrUpdateAdDTO createOrUpdateAdDTO);


    @Mapping(target = "pk", source = "ad.pk")
    @Mapping(target = "email", source = "ad.user.email")
    @Mapping(target = "phone", source = "ad.user.phone")
    @Mapping(target = "authorFirstName", source = "ad.user.firstName")
    @Mapping(target = "authorLastName", source = "ad.user.lastName")
    @Mapping(target = "image", expression = "java(\"/image/\" + ad.getImage().getId())")
    ExtendedAdDTO toExtendedAd(Ad adEntity);
}