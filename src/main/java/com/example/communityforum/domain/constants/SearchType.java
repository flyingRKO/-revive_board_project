package com.example.communityforum.domain.constants;

import lombok.Getter;

public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    ID("유저 ID");

    @Getter
    private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
