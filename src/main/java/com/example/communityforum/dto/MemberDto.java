package com.example.communityforum.dto;

import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.MemberRole;
import lombok.Getter;

@Getter
public class MemberDto {
    private  final String memberId;
    private  final String memberPassword;
    private  final String name;
    private  final String phone;
    private  final MemberRole role;
    private  final Boolean agreedTerm;

    private MemberDto(String memberId, String memberPassword, String name, String phone, MemberRole role, Boolean agreedTerm) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.agreedTerm = agreedTerm;
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
