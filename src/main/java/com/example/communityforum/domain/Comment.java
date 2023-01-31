package com.example.communityforum.domain;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Article articleId; // 게시글 (ID)
    private String content; // 본문
    private UserAccount userAccount; // 글쓴이
    private LocalDateTime createdAt; // 생성일시


}
