package com.example.communityforum.dto.response;

import com.example.communityforum.dto.BoardCommentDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardCommentResponse {
    private final Long id;
    private final String content;
    private final LocalDateTime registeredDate;
    private final String phone;
    private final String memberId;

    private BoardCommentResponse(Long id, String content, LocalDateTime registeredDate, String phone, String memberId) {
        this.id = id;
        this.content = content;
        this.registeredDate = registeredDate;
        this.phone = phone;
        this.memberId = memberId;
    }

    public static BoardCommentResponse of(Long id, String content, LocalDateTime registeredDate, String phone, String memberId){
        return new BoardCommentResponse(id, content, registeredDate, phone, memberId);
    }

    public static BoardCommentResponse from(BoardCommentDto dto) {
        return new BoardCommentResponse(
                dto.getId(),
                dto.getContent(),
                dto.getRegisteredDate(),
                dto.getMemberDto().getPhone(),
                dto.getMemberDto().getMemberId()

        );
    }
}
