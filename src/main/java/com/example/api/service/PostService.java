package com.example.api.service;

import com.example.api.dto.PostCreateRequestDto;
import com.example.api.dto.PostEditRequestDto;
import com.example.api.dto.PostResponseDto;
import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import com.example.api.exception.PostModificationDeniedException;
import com.example.api.exception.PostNotFoundException;
import com.example.api.mapper.PostMapper;
import com.example.api.repository.PostRepository;
import com.example.api.role.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostMapper mapper;
    private final UserService userService;
    private final Sort idAscSort = Sort.by(Sort.Direction.ASC, "id");

    public List<PostResponseDto> findAll(int page, int count) {
        return mapper.fromEntities(
                repository.findByDeletedIsNull(PageRequest.of(page, count, idAscSort))
        );
    }

    public PostResponseDto findById(long id) {
        return mapper.fromEntity(
                repository.findByDeletedIsNullAndId(id).orElseThrow(PostNotFoundException::new)
        );
    }

    public void create(PostCreateRequestDto request) {
        repository.save(
                PostEntity.builder()
                        .content(request.getContent())
                        .author(userService.meLikeEntity())
                        .attachment(request.getAttachment())
                        .build()
        );
    }

    public void edit(PostEditRequestDto request) {
        PostEntity post = repository.findByDeletedIsNullAndId(request.getId()).orElseThrow(PostNotFoundException::new);

        if (post.getAuthor().getId() != userService.me().getId()) {
            throw new PostModificationDeniedException();
        }

        post.setContent(request.getContent());
        post.setEdited(Instant.now());
        if (request.getAttachment() != null) {
            post.setAttachment(request.getAttachment());
        }
        repository.save(post);
    }

    public void delete(long id) {
        PostEntity post = repository.findById(id).orElseThrow(PostNotFoundException::new);
        boolean admin = isAdmin();

        if (post.getAuthor().getId() == userService.me().getId() || admin) {
            if (admin) {
                post.setDeletedByAdmin(true);
            }

            post.setDeleted(Instant.now());
            repository.save(post);
        } else {
            throw new PostModificationDeniedException();
        }
    }

    public void recover(long id) {
        PostEntity post = repository.findById(id).orElseThrow(PostNotFoundException::new);
        boolean admin = isAdmin();

        if (post.isDeletedByAdmin() && !admin) {
            throw new PostModificationDeniedException();
        }

        if ((post.getAuthor().getId() == userService.me().getId() || admin)) {
            if (admin) {
                post.setDeletedByAdmin(false);
            }

            post.setDeleted(null);
            repository.save(post);
        } else {
            throw new PostModificationDeniedException();
        }
    }

    public void like(long id) {
        PostEntity post = repository.findByDeletedIsNullAndId(id).orElseThrow(PostNotFoundException::new);
        int likes = post.getLikes() + 1;
        post.setLikes(likes);
        repository.save(post);
    }

    public void dislike(long id) {
        PostEntity post = repository.findByDeletedIsNullAndId(id).orElseThrow(PostNotFoundException::new);
        int dislikes = post.getDislikes() + 1;
        post.setDislikes(dislikes);
        repository.save(post);
    }

    private boolean isAdmin() {
        return userService.meLikeEntity()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains(Roles.ADMIN);
    }

}
