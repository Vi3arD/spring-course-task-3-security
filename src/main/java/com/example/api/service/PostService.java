package com.example.api.service;

import com.example.api.dto.PostCreateRequestDto;
import com.example.api.dto.PostEditRequestDto;
import com.example.api.dto.PostResponseDto;
import com.example.api.entity.PostEntity;
import com.example.api.mapper.PostMapper;
import com.example.api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostMapper mapper;
    private final UserService userService;
    private final Sort idAscSort = Sort.by(Sort.Direction.ASC, "id");

    public List<PostResponseDto> findAll(int page, int count) {
        return mapper.fromEntities(
                repository.findAll(PageRequest.of(page, count, idAscSort)).getContent()
        );
    }

    public List<PostResponseDto> getSubPostsByParentId(long parentId) {
        return mapper.fromEntities(repository.findByParent_Id(parentId));
    }

    public void create(PostCreateRequestDto request) {
        PostEntity parent = null;

        if (request.getParent() != 0) {
            parent = repository.getById(request.getParent());
        }

        repository.save(
                PostEntity.builder()
                        .content(request.getContent())
                        .author(userService.meLikeEntity())
                        .parent(parent)
                        .attachment(request.getAttachment())
                        .build()
        );
    }

    public void edit(PostEditRequestDto request) {
        PostEntity post = repository.findById(request.getId()).orElseThrow();
        post.setContent(request.getContent());
        post.setEdited(Instant.now());
        if(request.getAttachment() != null){
            post.setAttachment(request.getAttachment());
        }
        repository.save(post);
    }

    public void delete(long id){
        setDeletedMark(id, Instant.now());
    }

    public void recover(long id){
        setDeletedMark(id, null);
    }

    public void like(long id){
        PostEntity post = repository.findById(id).orElseThrow();
        int likes = post.getLikes() + 1;
        post.setLikes(likes);
        repository.save(post);
    }

    public void dislike(long id){
        PostEntity post = repository.findById(id).orElseThrow();
        int dislikes = post.getDislikes() + 1;
        post.setDislikes(dislikes);
        repository.save(post);
    }

    private void setDeletedMark(long id, Instant mark){
        PostEntity post = repository.findById(id).orElseThrow();
        if(!post.getPosts().isEmpty()){
            for(PostEntity sub : post.getPosts()){
                sub.setDeleted(mark);
            }
        }
        post.setDeleted(mark);
        repository.save(post);
    }

}
