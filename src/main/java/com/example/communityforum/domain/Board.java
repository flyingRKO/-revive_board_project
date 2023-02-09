package com.example.communityforum.domain;

import com.example.communityforum.domain.constants.BoardType;
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
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "registeredDate")
        })
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "memberId") private Member member; // 유저 정보

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType; // 구분

    @Setter @Column(nullable = false, length = 50) private String title; // 제목
    @Setter @Column(nullable = false, length = 500) private String content;  // 본문


    @OrderBy("registeredDate desc")
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<BoardComment> boardComments = new LinkedHashSet<>();


    @CreatedDate @Column(nullable = false) private LocalDateTime registeredDate; // 생성일시
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedDate; // 수정일시


    protected Board() {}

    private Board(Member member, BoardType boardType, String title, String content) {
        this.member = member;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
    }

    public static Board of(Member member, BoardType boardType, String title, String content) {
        return new Board(member, boardType, title, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return id != null && id.equals(board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
