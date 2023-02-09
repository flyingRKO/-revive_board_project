package com.example.communityforum.dto;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.BoardComment;
import com.example.communityforum.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardCommentDto {
    private  final Long id;
    private  final Long boardId;
    private  final MemberDto memberDto;
    private  final String content;
    private  final LocalDateTime registeredDate;

    private BoardCommentDto(Long id, Long boardId, MemberDto memberDto, String content, LocalDateTime registeredDate) {
        this.id = id;
        this.boardId = boardId;
        this.memberDto = memberDto;
        this.content = content;
        this.registeredDate = registeredDate;
    }

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
