package com.example.communityforum.dto.response;

import com.example.communityforum.dto.BoardCommentDto;

import java.time.LocalDateTime;

public record BoardCommentResponse(
        Long id,
        String content,
        LocalDateTime registeredDate,
        String phone,
        String memberId
) {

    public static BoardCommentResponse of(Long id, String content, LocalDateTime registeredDate, String phone, String memberId){
        return new BoardCommentResponse(id, content, registeredDate, phone, memberId);
    }

    public static BoardCommentResponse from(BoardCommentDto dto) {
        return new BoardCommentResponse(
                dto.id(),
                dto.content(),
                dto.registeredDate(),
                dto.memberDto().phone(),
                dto.memberDto().memberId()

        );
    }
}
