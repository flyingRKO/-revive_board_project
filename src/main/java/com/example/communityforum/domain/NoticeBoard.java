package com.example.communityforum.domain;

import com.example.communityforum.domain.constant.BoardType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class NoticeBoard {

    @Id
    @GeneratedValue
    private Long id;
    private String title; // 제목
    private String content;  // 본문

}
