package com.example.api.mapper;

import com.example.api.dto.PostResponseDto;
import com.example.api.dto.PostResponseDto.AuthorResponseDto;
import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostResponseDto fromEntity(PostEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setId( entity.getId() );
        postResponseDto.setAuthor( fromEntity( entity.getAuthor() ) );
        postResponseDto.setContent( entity.getContent() );
        postResponseDto.setCreated( entity.getCreated() );

        return postResponseDto;
    }

    @Override
    public List<PostResponseDto> fromEntities(List<PostEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PostResponseDto> list = new ArrayList<PostResponseDto>( entities.size() );
        for ( PostEntity postEntity : entities ) {
            list.add( fromEntity( postEntity ) );
        }

        return list;
    }

    @Override
    public AuthorResponseDto fromEntity(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        authorResponseDto.setId( entity.getId() );
        authorResponseDto.setName( entity.getName() );
        authorResponseDto.setAvatar( entity.getAvatar() );

        return authorResponseDto;
    }
}
