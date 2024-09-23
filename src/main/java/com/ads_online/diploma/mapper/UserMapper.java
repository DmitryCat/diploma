package com.ads_online.diploma.mapper;

import com.ads_online.diploma.dto.RegisterDTO;
import com.ads_online.diploma.dto.UpdateUserDTO;
import com.ads_online.diploma.dto.UserDTO;
import com.ads_online.diploma.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "email", source = "username")
    User registerDTOToUser(RegisterDTO register);

    @Mapping(target = "image", expression = "java(user.getImage() != null ? \"/image/\" + user.getImage().getId() : null)")
    @Mapping(source = "role", target = "role")
    UserDTO toUser(User userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    void updateUserToUser(UpdateUserDTO updateUser, @MappingTarget UserDTO user);
}