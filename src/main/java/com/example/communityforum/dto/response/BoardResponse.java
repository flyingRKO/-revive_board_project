package com.example.communityforum.dto.response;

import com.example.communityforum.domain.constants.BoardType;
import com.example.communityforum.dto.BoardDto;

import java.time.LocalDateTime;

public record BoardResponse(
        Long id,
        String title,
        String content,
        LocalDateTime registeredDate,
        String phone,
        String memberId,
        BoardType boardType
) {

    public static BoardResponse of(Long id, String title, String content, LocalDateTime registeredDate, String phone, String memberId, BoardType boardType){
        return new BoardResponse(id, title, content, registeredDate, phone, memberId, boardType);
    }

    public static BoardResponse from(BoardDto dto){
        return new BoardResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.registeredDate(),
                dto.memberDto().phone(),
                dto.memberDto().memberId(),
                dto.boardType()
        );
    }
}
