package com.example.communityforum.dto;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.constants.BoardType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BoardWithCommentsDto {
    private  final Long id;
    private  final MemberDto memberDto;
    private  final Set<BoardCommentDto> boardCommentDtos;
    private  final BoardType boardType;
    private  final String title;
    private  final String content;
    private  final LocalDateTime registeredDate;
    private  final LocalDateTime modifiedDate;

    private BoardWithCommentsDto(Long id,
                                MemberDto memberDto,
                                Set<BoardCommentDto> boardCommentDtos,
                                BoardType boardType,
                                String title,
                                String content,
                                LocalDateTime registeredDate,
                                LocalDateTime modifiedDate
    ) {
        this.id = id;
        this.memberDto = memberDto;
        this.boardCommentDtos = boardCommentDtos;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.registeredDate = registeredDate;
        this.modifiedDate = modifiedDate;
    }

    public static BoardWithCommentsDto of(Long id,
                                          MemberDto memberDto,
                                          Set<BoardCommentDto> boardCommentDtos,
                                          BoardType boardType,
                                          String title,
                                          String content,
                                          LocalDateTime registeredDate,
                                          LocalDateTime modifiedDate
    ) {
        return new BoardWithCommentsDto(id,
                memberDto,
                boardCommentDtos,
                boardType,
                title,
                content,
                registeredDate,
                modifiedDate);
    }

    public static BoardWithCommentsDto from(Board entity) {
        return new BoardWithCommentsDto(
                entity.getId(),
                MemberDto.from(entity.getMember()),
                entity.getBoardComments().stream()
                        .map(BoardCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getBoardType(),
                entity.getTitle(),
                entity.getContent(),
                entity.getRegisteredDate(),
                entity.getModifiedDate()
        );
    }
}
