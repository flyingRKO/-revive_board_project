package com.example.communityforum.domain.constants;

import lombok.Getter;

public enum BoardType {
    NOTICE("공지사항"),
    FORUM("자유게시판");

    @Getter
    private final String description;

    BoardType(String description) {
        this.description = description;
    }
}
