package com.example.communityforum.dto.response;

import com.example.communityforum.dto.BoardWithCommentsDto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record BoardWithCommentResponse(
        Long id,
        String title,
        String content,
        LocalDateTime registeredDate,
        String phone,
        String memberId,
        Set<BoardCommentResponse> boardCommentResponses
) {

    public static BoardWithCommentResponse of(Long id,
                                              String title,
                                              String content,
                                              LocalDateTime registeredDate,
                                              String phone,
                                              String memberId,
                                              Set<BoardCommentResponse> boardCommentResponses
    ) {
        return new BoardWithCommentResponse(id, title, content, registeredDate, phone, memberId, boardCommentResponses);
    }

    public static BoardWithCommentResponse from(BoardWithCommentsDto dto) {
        return new BoardWithCommentResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.registeredDate(),
                dto.memberDto().phone(),
                dto.memberDto().memberId(),
                dto.boardCommentDtos().stream()
                        .map(BoardCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
}
