package com.example.api.repository;

import com.example.api.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByDeletedIsNull(Pageable pageable);

    Optional<PostEntity> findByDeletedIsNullAndId(long id);

    @Modifying
    @Query("UPDATE PostEntity p SET p.likes = p.likes + 1 where p.id = :id")
    void like(@Param("id") long id);

    @Modifying
    @Query("UPDATE PostEntity p SET p.dislikes = p.dislikes + 1 where p.id = :id")
    void dislike(@Param("id") long id);
}
