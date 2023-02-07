package com.example.communityforum.dto.request;

import com.example.communityforum.dto.BoardDto;
import com.example.communityforum.dto.UserAccountDto;
import lombok.Getter;

@Getter
public class BoardRequest {
    private final String title;
    private final String content;

    private BoardRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static BoardRequest of(String title, String content) {
        return new BoardRequest(title, content);
    }

    public BoardDto toDto(UserAccountDto userAccountDto){
        return BoardDto.of(
                userAccountDto,
                title,
                content
        );
    }
}
