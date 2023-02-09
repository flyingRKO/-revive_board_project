package com.example.communityforum.domain.member;

import com.example.communityforum.domain.constants.MemberRole;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    public void test() {
        Member user = new Member("test", "test", "test", "01012345678", MemberRole.ADMIN, true);

        assertEquals(user.getMemberId(), "test");
        assertEquals(user.getMemberPassword(), "test");
        assertEquals(user.getName(), "test");
        assertEquals(user.getPhone(), "01012345678");
        assertEquals(user.getRole(), MemberRole.ADMIN);
        assertEquals(user.getAgreedTerms(), true);

    }

}