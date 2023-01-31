package com.example.communityforum.domain;

import com.example.communityforum.domain.constant.Category;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Article {
    private Long id;
    private String title; // 제목
    private String content;  // 본문
    private Category category; // 구분
    private UserAccount userAccount; // 글쓴이
    private LocalDateTime registeredDate; // 생성일시
    private LocalDateTime modifiedDate; // 수정일시

}
