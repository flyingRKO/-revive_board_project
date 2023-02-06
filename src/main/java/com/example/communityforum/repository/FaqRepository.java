package com.example.communityforum.repository;

import com.example.communityforum.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    Faq save(Faq faq);
    Optional<Faq> findById(Long id);
    List<Faq> findAll();
}
