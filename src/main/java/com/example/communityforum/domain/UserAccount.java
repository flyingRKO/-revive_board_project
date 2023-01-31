package com.example.communityforum.domain;

import com.example.communityforum.domain.constant.Role;

public class UserAccount {
    private Long id;
    private String userId; // 로그인 아이디
    private String password; // 비밀번호
    private String userName; // 사용자 본명
    private String phone; // 휴대전화번호
    private Role role; // 사용자 역할
    private boolean term; // 사용자약관동의
}
