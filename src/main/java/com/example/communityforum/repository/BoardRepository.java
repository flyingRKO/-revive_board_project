package com.example.communityforum.repository;

import com.example.communityforum.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;


public interface BoardRepository extends JpaRepository<Board, Long>{

}
