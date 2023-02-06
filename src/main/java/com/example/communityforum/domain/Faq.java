package com.example.communityforum.domain;

import com.example.communityforum.domain.constants.FaqType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder // 빌더를 사용할 수 있게 함
@ToString
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private FaqType type;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String content;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredDate;


}
