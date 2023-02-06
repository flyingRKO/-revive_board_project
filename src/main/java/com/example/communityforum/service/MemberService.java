package com.example.communityforum.service;

import com.example.communityforum.domain.member.Member;
import com.example.communityforum.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    //test용 코드
//    public void saveMember(Member member) {
//        memberRepository.save(member);
//    }
//
//    public Member getMember(String memberId) {
//        return memberRepository.findByMemberId(memberId).orElseThrow();
//    }

    public Member create(String memberId, String memberPassword, String name, String phone, Boolean term) {
        Member findMember = memberRepository.findByMemberId(memberId).orElse(null);
        if (findMember != null) {
            throw new DataIntegrityViolationException("이미 존재하는 아이디입니다.");
        }
        Member member = Member.of(memberId,  passwordEncoder.encode(memberPassword), name, phone, term);
        memberRepository.save(member);
        return member;
    }
}
