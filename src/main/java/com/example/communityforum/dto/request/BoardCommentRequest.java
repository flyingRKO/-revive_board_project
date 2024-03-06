package com.example.communityforum.dto.request;

import com.example.communityforum.dto.BoardCommentDto;
import com.example.communityforum.dto.MemberDto;


public record BoardCommentRequest(
        Long boardId,
        String content
) {
    public static BoardCommentRequest of(Long boardId, String content) {
        return new BoardCommentRequest(boardId, content);
    }

    public BoardCommentDto toDto(MemberDto memberDto){
        return BoardCommentDto.of(
                boardId,
                memberDto,
                content
        );
    }
}
