package com.example.communityforum.dto;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.UserAccount;
import com.example.communityforum.domain.constant.BoardType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDto {
    private final Long id;
    private final UserAccountDto userAccountDto;
    private final BoardType boardType;
    private final String title;
    private final String content;
    private final LocalDateTime registeredDate;
    private final LocalDateTime modifiedDate;

    private BoardDto(Long id, UserAccountDto userAccountDto, BoardType boardType, String title, String content, LocalDateTime registeredDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.registeredDate = registeredDate;
        this.modifiedDate = modifiedDate;
    }


    public static BoardDto of(Long id,
                              UserAccountDto userAccountDto,
                              BoardType boardType,
                              String title,
                              String content ,
                              LocalDateTime registeredDate,
                              LocalDateTime modifiedDate
    ){
        return new BoardDto(id, userAccountDto, boardType, title, content, registeredDate, modifiedDate);
    }

    public static BoardDto of(UserAccountDto userAccountDto, String title, String content){
        return new BoardDto(null, userAccountDto, null, title,content,null,null);
    }

    public static BoardDto from(Board entity) {
        return new BoardDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getBoardType(),
                entity.getTitle(),
                entity.getContent(),
                entity.getRegisteredDate(),
                entity.getModifiedDate()
        );
    }

    public Board toEntity(UserAccount userAccount) {
        return Board.of(
                userAccount,
                BoardType.FORUM,
                title,
                content
        );
    }
}
