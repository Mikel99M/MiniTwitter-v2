package com.example.demo.mapper;

import com.example.demo.domain.Like;
import com.example.demo.dto.LikeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    List<LikeDto> likeListToLikeDtoList(List<Like> allLikes);

    @Mapping(target = "userId", source = "likeGiver.id")
    @Mapping(target = "postId", source = "likedPost.id")
    LikeDto likeToLikeDto(Like likeById);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likedAt", ignore = true)
    Like likeDtoToLike(LikeDto likeDto);
}
