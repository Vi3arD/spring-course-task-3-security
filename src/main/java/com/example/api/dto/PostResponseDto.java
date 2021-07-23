package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResponseDto {
  private long id;
  private AuthorResponseDto author;
  private String content;
  private String attachment;
  private Instant created;
  private Instant edited;
  private int likes;
  private int dislikes;

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class AuthorResponseDto {
    private long id;
    private String name;
    private String avatar;
  }
}
