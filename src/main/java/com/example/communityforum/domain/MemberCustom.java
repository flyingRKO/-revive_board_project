package com.example.communityforum.domain;

import com.example.communityforum.domain.constants.MemberRole;
import com.example.communityforum.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class MemberCustom extends User {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String name;

    public MemberCustom(String username, String password, String name, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.name = name;
    }

    public static MemberCustom of(String username, String password, String name){
        Set<MemberRole> memberRoles = Set.of(MemberRole.USER);
        return new MemberCustom(
                username,
                password,
                name,
                memberRoles.stream()
                        .map(MemberRole::getValue)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static MemberCustom from(MemberDto dto){
        return MemberCustom.of(
                dto.getMemberId(),
                dto.getMemberPassword(),
                dto.getName()
        );
    }

    public MemberDto toDto(){
        return MemberDto.of(
                super.getUsername(),
                super.getPassword(),
                name
        );
    }


}
