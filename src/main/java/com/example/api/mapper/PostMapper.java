package com.example.api.mapper;

import com.example.api.dto.PostResponseDto;
import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
  PostResponseDto fromEntity(PostEntity entity);
  List<PostResponseDto> fromEntities(List<PostEntity> entities);
  PostResponseDto.AuthorResponseDto fromEntity(UserEntity entity);
}
