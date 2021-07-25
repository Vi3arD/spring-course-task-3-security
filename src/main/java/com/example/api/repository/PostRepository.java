package com.example.api.repository;

import com.example.api.entity.PostEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByDeletedIsNull(Pageable pageable);
    Optional<PostEntity> findByDeletedIsNullAndId(long id);
}
