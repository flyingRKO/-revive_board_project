package com.example.communityforum.service;

import com.example.communityforum.domain.member.Member;
import com.example.communityforum.domain.member.MemberCustom;
import com.example.communityforum.domain.member.MemberRole;
import com.example.communityforum.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        Optional<Member> _siteUser = this.memberRepository.findByMemberId(memberId);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }

        Member siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        MemberRole _siteUserRole = siteUser.getRole();

        if (MemberRole.ADMIN.getValue().equals("ROLE_"+_siteUserRole)) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        return new MemberCustom(siteUser.getMemberId(), siteUser.getMemberPassword(), siteUser.getName(), authorities);
    }
}