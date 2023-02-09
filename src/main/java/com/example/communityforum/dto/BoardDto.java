package com.example.communityforum.dto;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.BoardType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDto {
    private final Long id;
    private final MemberDto memberDto;
    private final BoardType boardType;
    private final String title;
    private final String content;
    private final LocalDateTime registeredDate;
    private final LocalDateTime modifiedDate;

    private BoardDto(Long id, MemberDto memberDto, BoardType boardType, String title, String content, LocalDateTime registeredDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.memberDto = memberDto;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.registeredDate = registeredDate;
        this.modifiedDate = modifiedDate;
    }


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
        return new BoardDto(null, memberDto, null, title,content,null,null);
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

    public Board toEntity(Member member) {
        return Board.of(
                member,
                BoardType.FORUM,
                title,
                content
        );
    }
}
