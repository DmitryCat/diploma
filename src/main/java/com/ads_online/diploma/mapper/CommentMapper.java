package com.ads_online.diploma.mapper;
import com.ads_online.diploma.dto.CommentDTO;
import com.ads_online.diploma.dto.CreateOrUpdateCommentDTO;
import com.ads_online.diploma.entity.Comment;
import com.ads_online.diploma.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", expression = "java(user.getImage() != null ? \"/image/\" + user.getImage().getId() : \"\")")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    CommentDTO commentToCommentDTO(Comment comment, User user);
    @Mappings({
            @Mapping(target = "user.id", source = "author"),
            @Mapping(target = "user.firstName", source = "authorFirstName")
    })
    Comment commentDTOToComment(CommentDTO comment);
    @Mappings({
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "pk", ignore = true)
    })
    Comment createOrUpdateCommentDTOToComment(CreateOrUpdateCommentDTO createOrUpdateComment);
}
