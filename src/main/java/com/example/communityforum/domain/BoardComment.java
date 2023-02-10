package com.example.communityforum.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "registeredDate")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private NoticeBoard noticeBoard; // 게시글 (ID)
    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private UserAccount userAccount; // 유저 정보
    @Setter @Column(nullable = false, length = 50) private String content; // 본문
    @CreatedDate @Column(nullable = false) private LocalDateTime registeredDate; // 생성일시

    protected BoardComment() {}

    private BoardComment(NoticeBoard noticeBoard, UserAccount userAccount, String content) {
        this.noticeBoard = noticeBoard;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static BoardComment of(NoticeBoard noticeBoard, UserAccount userAccount, String content){
        return new BoardComment(noticeBoard, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardComment)) return false;
        BoardComment boardComment = (BoardComment) o;
        return id != null && id.equals(boardComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}