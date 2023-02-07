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
    private final String userName;

    private BoardCommentResponse(Long id, String content, LocalDateTime registeredDate, String phone, String userName) {
        this.id = id;
        this.content = content;
        this.registeredDate = registeredDate;
        this.phone = phone;
        this.userName = userName;
    }

    public static BoardCommentResponse of(Long id, String content, LocalDateTime registeredDate, String phone, String userName){
        return new BoardCommentResponse(id, content, registeredDate, phone, userName);
    }

    public static BoardCommentResponse from(BoardCommentDto dto) {
        return new BoardCommentResponse(
                dto.getId(),
                dto.getContent(),
                dto.getRegisteredDate(),
                dto.getUserAccountDto().getPhone(),
                dto.getUserAccountDto().getUserName()

        );
    }
}
