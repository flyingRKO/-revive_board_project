package com.example.communityforum.dto;

import com.example.communityforum.domain.member.Member;
import com.example.communityforum.domain.member.MemberRole;

public class MemberDto {

    private String memberId;
    private String memberPassword;
    private String name;
    private String phone;
    private MemberRole role;
    private Boolean agreedTerms;

    private MemberDto(String memberId, String memberPassword, String name, String phone, MemberRole role, Boolean agreedTerms) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.agreedTerms = agreedTerms;
    }

    public static MemberDto of(String memberId, String memberPassword, String name, String phone, MemberRole role, Boolean agreedTerms) {
        return new MemberDto(memberId, memberPassword, name, phone, role, agreedTerms);
    }

    public static MemberDto from(Member entity) {
        return new MemberDto(
                entity.getMemberId(),
                entity.getMemberPassword(),
                entity.getName(),
                entity.getPhone(),
                entity.getRole(),
                entity.getAgreedTerms()
        );
    }
}