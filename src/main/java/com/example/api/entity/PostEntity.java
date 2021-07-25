package com.example.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    private String content;

    private String attachment;

    @Column(insertable = false, updatable = false)
    private Instant created;

    private Instant deleted;

    @Column(name = "deleted_by_admin")
    private boolean deletedByAdmin;

    private Instant edited;

    private int likes;

    private int dislikes;
}
