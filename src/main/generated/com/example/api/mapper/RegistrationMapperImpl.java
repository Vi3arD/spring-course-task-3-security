package com.example.api.mapper;

import com.example.api.dto.RegistrationRequestDto;
import com.example.api.dto.RegistrationResponseDto;
import com.example.api.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public UserEntity fromDto(RegistrationRequestDto dto, String password, List<GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        if ( dto == null && password == null && authorities == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        if ( dto != null ) {
            if ( dto.getAvatar() != null ) {
                userEntity.setAvatar( dto.getAvatar() );
            }
            userEntity.setName( dto.getName() );
            userEntity.setUsername( dto.getUsername() );
        }
        if ( password != null ) {
            userEntity.setPassword( password );
        }
        if ( authorities != null ) {
            List<GrantedAuthority> list = authorities;
            if ( list != null ) {
                userEntity.setAuthorities( new ArrayList<GrantedAuthority>( list ) );
            }
        }
        userEntity.setAccountNonExpired( accountNonExpired );
        userEntity.setAccountNonLocked( accountNonLocked );
        userEntity.setCredentialsNonExpired( credentialsNonExpired );
        userEntity.setEnabled( enabled );

        return userEntity;
    }

    @Override
    public RegistrationResponseDto fromEntity(UserEntity entity, String token) {
        if ( entity == null && token == null ) {
            return null;
        }

        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto();

        if ( entity != null ) {
            registrationResponseDto.setId( entity.getId() );
            registrationResponseDto.setUsername( entity.getUsername() );
            registrationResponseDto.setName( entity.getName() );
            registrationResponseDto.setAvatar( entity.getAvatar() );
        }
        if ( token != null ) {
            registrationResponseDto.setToken( token );
        }

        return registrationResponseDto;
    }
}
