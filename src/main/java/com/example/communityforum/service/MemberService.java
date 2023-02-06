package com.example.communityforum.service;

import com.example.communityforum.domain.member.Member;
import com.example.communityforum.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Member getMember(String memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow();
    }
}
