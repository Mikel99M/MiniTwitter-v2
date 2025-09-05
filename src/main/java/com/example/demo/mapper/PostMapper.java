package com.example.demo.mapper;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostCreateDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.PostUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "postedAt", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "author", ignore = true)
    Post postCreatedDtoToPost(PostCreateDto postDto);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "likeCount", expression = "java(post.getLikeCount())")
    PostResponseDto postToPostResponseDto(Post post);

    PostUpdateDto postToPostUpdatedDto(Post post);

    List<PostResponseDto> postListToPostResponseDtoList(List<Post> postsMadeByUser);
}
