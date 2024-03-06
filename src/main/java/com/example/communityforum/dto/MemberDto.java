package com.example.communityforum.dto;

import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.MemberRole;

public record MemberDto(
        String memberId,
        String memberPassword,
        String name,
        String phone,
        MemberRole role,
        Boolean agreedTerm
) {
    public static MemberDto of(String userName, String password, String name){
        return new MemberDto(userName, password, name, null, MemberRole.USER, true);
    }
    public static MemberDto of(String userId, String password, String name, String phone, MemberRole role, boolean term){
        return new MemberDto(userId, password, name, phone, role, term);
    }

    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getMemberId(),
                entity.getMemberPassword(),
                entity.getName(),
                entity.getPhone(),
                entity.getRole(),
                entity.isAgreedTerm()
        );
    }

    public Member toEntity(){
        return Member.of(
                memberId,
                memberPassword,
                name,
                phone,
                role,
                agreedTerm
        );
    }
}
