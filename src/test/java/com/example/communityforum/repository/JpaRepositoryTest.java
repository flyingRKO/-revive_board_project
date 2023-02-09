package com.example.communityforum.repository;

import com.example.communityforum.domain.Faq;
import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.FaqType;
import com.example.communityforum.domain.constants.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@SpringBootTest
public class JpaRepositoryTest {

    private final FaqRepository faqRepository;

    @Autowired
    public JpaRepositoryTest(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Test
    void 저장(){
        Member user = Member.of("test", "test", "test", "01012345678", MemberRole.ADMIN, true);

        Faq faq = Faq.builder()
                .member(user)
                .title("abcde")
                .content("contents")
                .type(FaqType.ETC)
                .registeredDate(LocalDateTime.now()).build();

        faqRepository.save(faq);

    }


    @Test
    void 출력(){
        // Given

        // When
        List<Faq> articles = faqRepository.findAll();

        for(Faq faq: articles)
        {
            System.out.println(faq);
        }
    }
}
