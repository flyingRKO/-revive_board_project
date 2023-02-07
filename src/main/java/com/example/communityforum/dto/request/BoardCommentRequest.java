package com.example.communityforum.dto.request;

import com.example.communityforum.dto.BoardCommentDto;
import com.example.communityforum.dto.UserAccountDto;
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

    public BoardCommentDto toDto(UserAccountDto userAccountDto){
        return BoardCommentDto.of(
                boardId,
                userAccountDto,
                content
        );
    }
}
