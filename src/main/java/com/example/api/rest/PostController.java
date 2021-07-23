package com.example.api.rest;

import com.example.api.dto.PostCreateRequestDto;
import com.example.api.dto.PostEditRequestDto;
import com.example.api.dto.PostResponseDto;
import com.example.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping
    public List<PostResponseDto> getAll(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "50") @Min(0) @Max(50) int count
    ) {
        return service.findAll(page, count);
    }

    @GetMapping("/{id}")
    public List<PostResponseDto> getSubPostsByParentId(@RequestParam @Min(0) long id) {
        return service.getSubPostsByParentId(id);
    }

    @PostMapping
    public void createPost(@RequestBody PostCreateRequestDto request) {
        service.create(request);
    }

    @PutMapping
    public void editPost(@RequestBody PostEditRequestDto request) {
        service.edit(request);
    }

    @DeleteMapping
    public void delete(@RequestParam @Min(0) long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public void recoverPost(@RequestParam @Min(0) long id) {
        service.recover(id);
    }

    @PutMapping("/{id}/like")
    public void like(@RequestParam @Min(0) long id) {
        service.like(id);
    }

    @PutMapping("/{id}/dislike")
    public void dislike(@RequestParam @Min(0) long id) {
        service.dislike(id);
    }

}
