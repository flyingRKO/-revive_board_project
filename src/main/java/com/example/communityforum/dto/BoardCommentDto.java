package com.example.communityforum.dto;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.BoardComment;
import com.example.communityforum.domain.UserAccount;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardCommentDto {
    private  final Long id;
    private  final Long boardId;
    private  final UserAccountDto userAccountDto;
    private  final String content;
    private  final LocalDateTime registeredDate;

    private BoardCommentDto(Long id, Long boardId, UserAccountDto userAccountDto, String content, LocalDateTime registeredDate) {
        this.id = id;
        this.boardId = boardId;
        this.userAccountDto = userAccountDto;
        this.content = content;
        this.registeredDate = registeredDate;
    }

    public static BoardCommentDto of(Long boardId, UserAccountDto userAccountDto, String content){
        return new BoardCommentDto(null, boardId, userAccountDto, content, null);
    }

    public static BoardCommentDto of(Long id, Long boardId, UserAccountDto userAccountDto, String content, LocalDateTime registeredDate){
        return new BoardCommentDto(id, boardId, userAccountDto, content, registeredDate);
    }

    public static BoardCommentDto from(BoardComment entity){
        return new BoardCommentDto(
                entity.getId(),
                entity.getBoard().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getRegisteredDate()
        );
    }

    public BoardComment toEntity(Board board, UserAccount userAccount){
        return BoardComment.of(
                board,
                userAccount,
                content
        );
    }

}
