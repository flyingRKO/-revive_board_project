package com.example.communityforum.dto.request;

import com.example.communityforum.dto.BoardDto;
import com.example.communityforum.dto.MemberDto;

public record BoardRequest(
        String title,
        String content
) {

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
