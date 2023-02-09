package com.example.communityforum.dto.request;

import com.example.communityforum.dto.BoardCommentDto;
import com.example.communityforum.dto.MemberDto;
import lombok.Getter;

@Getter
public class BoardCommentRequest {
    private final Long boardId;
    private final String content;

    private BoardCommentRequest(Long boardId, String content) {
        this.boardId = boardId;
        this.content = content;
    }

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
