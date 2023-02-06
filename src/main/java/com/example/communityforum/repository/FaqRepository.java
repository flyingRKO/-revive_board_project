package com.example.communityforum.repository;

import com.example.communityforum.domain.Faq;
import com.example.communityforum.domain.constants.FaqType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    Faq save(Faq faq);
    Optional<Faq> findById(Long id);
    Page<Faq> findAll(Pageable pageable);
    Page<Faq> findAllByType(FaqType type, Pageable pageable);
}
