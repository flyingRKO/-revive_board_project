package com.example.communityforum.service;

import com.example.communityforum.domain.Faq;
import com.example.communityforum.domain.constants.FaqType;
import com.example.communityforum.repository.FaqRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;
    public Page<Faq> listAll(Pageable pageable){
        return faqRepository.findAll(pageable);
    }

    public void save(Faq faq) {
        // 게시물 번호로 조회하여 데이가 있는지
       faqRepository.save(faq);
    }
}
