package com.example.communityforum.domain.member;

import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.MemberRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    @Test
    public void test() {
        Member user = Member.of("test", "test", "test", "01012345678", MemberRole.ADMIN, true);

        assertEquals(user.getMemberId(), "test");
        assertEquals(user.getMemberPassword(), "test");
        assertEquals(user.getName(), "test");
        assertEquals(user.getPhone(), "01012345678");
        assertEquals(user.getRole(), MemberRole.ADMIN);
        assertEquals(user.isAgreedTerm(), true);

    }

}