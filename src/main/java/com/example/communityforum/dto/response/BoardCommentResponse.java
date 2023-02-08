package com.example.communityforum.dto.response;

import com.example.communityforum.domain.Board;
import com.example.communityforum.dto.BoardCommentDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardCommentResponse {
    private final Long id;
    private final String content;
    private final LocalDateTime registeredDate;
    private final String phone;
    private final String userId;

    private BoardCommentResponse(Long id, String content, LocalDateTime registeredDate, String phone, String userId) {
        this.id = id;
        this.content = content;
        this.registeredDate = registeredDate;
        this.phone = phone;
        this.userId = userId;
    }

    public static BoardCommentResponse of(Long id, String content, LocalDateTime registeredDate, String phone, String userId){
        return new BoardCommentResponse(id, content, registeredDate, phone, userId);
    }

    public static BoardCommentResponse from(BoardCommentDto dto) {
        return new BoardCommentResponse(
                dto.getId(),
                dto.getContent(),
                dto.getRegisteredDate(),
                dto.getUserAccountDto().getPhone(),
                dto.getUserAccountDto().getUserId()

        );
    }
}
