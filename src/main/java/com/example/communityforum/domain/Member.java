package com.example.communityforum.domain;

import com.example.communityforum.domain.constants.MemberRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table
@Entity
public class Member {
    @Id
    @Column(length = 10)
    private String memberId; // 로그인 아이디
    @Setter @Column(length = 60, nullable = false) private String memberPassword; // 비밀번호
    @Setter @Column(nullable = false, length = 10) private String name; // 사용자 본명
    @Setter @Column(nullable = false, length = 11) private String phone; // 휴대전화번호

    @Setter
    @Column(columnDefinition = "ENUM('ADMIN','USER')")
    @Enumerated(EnumType.STRING)
    private MemberRole role = MemberRole.USER; // 사용자 역할

    @Setter @Column(name = "term", nullable = false) private boolean agreedTerm; // 사용자약관동의

    protected Member() {}

    private Member(String memberId, String memberPassword, String name, String phone, MemberRole role, Boolean agreedTerm) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.agreedTerm = agreedTerm;
    }

    public static Member of(String memberId, String memberPassword, String name, String phone, Boolean agreedTerm) {
        return new Member(memberId, memberPassword, name, phone, null, agreedTerm);
    }
    public static Member of(String memberId, String memberPassword, String name, String phone, MemberRole role, Boolean agreedTerm) {
        return new Member(memberId, memberPassword, name, phone, role, agreedTerm);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return memberId != null && memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
