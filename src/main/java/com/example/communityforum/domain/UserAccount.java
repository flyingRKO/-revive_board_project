package com.example.communityforum.domain;

import com.example.communityforum.domain.constant.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table
@Entity
public class UserAccount {
    @Id
    @Column(length = 10)
    private String userId; // 로그인 아이디
    @Setter @Column(nullable = false) private String password; // 비밀번호
    @Setter @Column(nullable = false, length = 10) private String userName; // 사용자 본명
    @Setter @Column(nullable = false, length = 11) private String phone; // 휴대전화번호

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role; // 사용자 역할

    @Setter @Column(nullable = false) private boolean term; // 사용자약관동의

    protected UserAccount() {}

    private UserAccount(String userId, String password, String userName, String phone,Role role, boolean term) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.role = role;
        this.term = term;
    }

    public static UserAccount of(String userId, String password, String userName, String phone, Role role, boolean term) {
        return new UserAccount(userId, password, userName, phone, role, term);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        UserAccount userAccount = (UserAccount) o;
        return userId != null && userId.equals(userAccount.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
