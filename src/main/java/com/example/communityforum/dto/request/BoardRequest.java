package com.example.communityforum.dto.request;

import com.example.communityforum.dto.BoardDto;
import com.example.communityforum.dto.MemberDto;
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

    public BoardDto toDto(MemberDto memberDto){
        return BoardDto.of(
                memberDto,
                title,
                content
        );
    }
}
