package com.example.communityforum.dto;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.UserAccount;
import com.example.communityforum.domain.constant.BoardType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BoardWithCommentsDto {
    private  final Long id;
    private  final UserAccountDto userAccountDto;
    private  final Set<BoardCommentDto> boardCommentDtos;
    private  final BoardType boardType;
    private  final String title;
    private  final String content;
    private  final LocalDateTime registeredDate;
    private  final LocalDateTime modifiedDate;

    private BoardWithCommentsDto(Long id,
                                UserAccountDto userAccountDto,
                                Set<BoardCommentDto> boardCommentDtos,
                                BoardType boardType,
                                String title,
                                String content,
                                LocalDateTime registeredDate,
                                LocalDateTime modifiedDate
    ) {
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.boardCommentDtos = boardCommentDtos;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.registeredDate = registeredDate;
        this.modifiedDate = modifiedDate;
    }

    public static BoardWithCommentsDto of(Long id,
                                          UserAccountDto userAccountDto,
                                          Set<BoardCommentDto> boardCommentDtos,
                                          BoardType boardType,
                                          String title,
                                          String content,
                                          LocalDateTime registeredDate,
                                          LocalDateTime modifiedDate
    ) {
        return new BoardWithCommentsDto(id,
                userAccountDto,
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
                UserAccountDto.from(entity.getUserAccount()),
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
