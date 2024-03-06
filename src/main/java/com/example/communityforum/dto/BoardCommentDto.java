package com.example.communityforum.dto;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.BoardComment;
import com.example.communityforum.domain.Member;

import java.time.LocalDateTime;

public record BoardCommentDto(
        Long id,
        Long boardId,
        MemberDto memberDto,
        String content,
        LocalDateTime registeredDate
) {

    public static BoardCommentDto of(Long boardId, MemberDto memberDto, String content){
        return new BoardCommentDto(null, boardId, memberDto, content, null);
    }

    public static BoardCommentDto of(Long id, Long boardId, MemberDto memberDto, String content, LocalDateTime registeredDate){
        return new BoardCommentDto(id, boardId, memberDto, content, registeredDate);
    }

    public static BoardCommentDto from(BoardComment entity){
        return new BoardCommentDto(
                entity.getId(),
                entity.getBoard().getId(),
                MemberDto.from(entity.getMember()),
                entity.getContent(),
                entity.getRegisteredDate()
        );
    }

    public BoardComment toEntity(Board board, Member member){
        return BoardComment.of(
                board,
                member,
                content
        );
    }

}
