package com.example.communityforum.dto;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.BoardType;

import java.time.LocalDateTime;

public record BoardDto(
        Long id,
        MemberDto memberDto,
        BoardType boardType,
        String title,
        String content,
        LocalDateTime registeredDate,
        LocalDateTime modifiedDate
) {

    public static BoardDto of(Long id,
                              MemberDto memberDto,
                              BoardType boardType,
                              String title,
                              String content ,
                              LocalDateTime registeredDate,
                              LocalDateTime modifiedDate
    ){
        return new BoardDto(id, memberDto, boardType, title, content, registeredDate, modifiedDate);
    }

    public static BoardDto of(MemberDto memberDto, String title, String content){
        return new BoardDto(null, memberDto, BoardType.FORUM, title,content,null,null);
    }

    public static BoardDto from(Board entity) {
        return new BoardDto(
                entity.getId(),
                MemberDto.from(entity.getMember()),
                entity.getBoardType(),
                entity.getTitle(),
                entity.getContent(),
                entity.getRegisteredDate(),
                entity.getModifiedDate()
        );
    }

    public Board toForumEntity(Member member) {
        return Board.of(
                member,
                BoardType.FORUM,
                title,
                content
        );
    }

    public Board toNoticeEntity(Member member) {
        return Board.of(
                member,
                BoardType.NOTICE,
                title,
                content
        );
    }
}
