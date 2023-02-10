package com.example.communityforum.domain;

import com.example.communityforum.domain.constant.BoardType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table
@EntityListeners(AuditingEntityListener.class)
@Entity
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType; // 구분

    @Setter @Column(nullable = false, length = 50) private String title; // 제목
    @Setter @Column(nullable = false, length = 500) private String content;  // 본문


    @OrderBy("id")
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<BoardComment> boardComments = new LinkedHashSet<>();


    @CreatedDate
    @Column (nullable = true)
    private LocalDateTime registeredDate; // 생성일시

    @LastModifiedDate
    @Column (nullable = true)
    private LocalDateTime modifiedDate; // 수정일시


    protected NoticeBoard() {}

    private NoticeBoard(BoardType boardType, String title, String content) {
        this.boardType = boardType;
        this.title = title;
        this.content = content;
    }

    public static NoticeBoard of(BoardType boardType, String title, String content) {
        return new NoticeBoard(boardType, title, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoticeBoard)) return false;
        NoticeBoard noticeBoard = (NoticeBoard) o;
        return id != null && id.equals(noticeBoard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}