package com.example.api.mapper;

import com.example.api.domain.UserInfo;
import com.example.api.dto.UserInfoResponseDto;
import com.example.api.entity.UserEntity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserDetailsMapperImpl implements UserDetailsMapper {

    @Override
    public UserInfo fromEntity(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        List<GrantedAuthority> authorities = null;
        long id = 0L;
        String name = null;
        String username = null;
        String password = null;
        String avatar = null;
        boolean accountNonExpired = false;
        boolean accountNonLocked = false;
        boolean credentialsNonExpired = false;
        boolean enabled = false;
        Instant created = null;

        List<GrantedAuthority> list = entity.getAuthorities();
        if ( list != null ) {
            authorities = new ArrayList<GrantedAuthority>( list );
        }
        id = entity.getId();
        name = entity.getName();
        username = entity.getUsername();
        password = entity.getPassword();
        avatar = entity.getAvatar();
        accountNonExpired = entity.isAccountNonExpired();
        accountNonLocked = entity.isAccountNonLocked();
        credentialsNonExpired = entity.isCredentialsNonExpired();
        enabled = entity.isEnabled();
        created = entity.getCreated();

        UserInfo userInfo = new UserInfo( id, name, username, password, avatar, authorities, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, created );

        return userInfo;
    }

    @Override
    public UserInfoResponseDto fromDomain(UserInfo info) {
        if ( info == null ) {
            return null;
        }

        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();

        userInfoResponseDto.setId( info.getId() );
        userInfoResponseDto.setName( info.getName() );
        userInfoResponseDto.setUsername( info.getUsername() );
        userInfoResponseDto.setAvatar( info.getAvatar() );
        List<GrantedAuthority> list = info.getAuthorities();
        if ( list != null ) {
            userInfoResponseDto.setAuthorities( new ArrayList<GrantedAuthority>( list ) );
        }
        userInfoResponseDto.setAccountNonExpired( info.isAccountNonExpired() );
        userInfoResponseDto.setAccountNonLocked( info.isAccountNonLocked() );
        userInfoResponseDto.setCredentialsNonExpired( info.isCredentialsNonExpired() );
        userInfoResponseDto.setEnabled( info.isEnabled() );
        userInfoResponseDto.setCreated( info.getCreated() );

        return userInfoResponseDto;
    }
}
