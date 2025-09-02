package com.example.demo.mapper;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "author", ignore = true)
    Post postDtoToPost(PostDto postDto);

    PostDto postPostToPostDto(Post postById);
}
