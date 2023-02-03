package com.example.communityforum.repository;

import com.example.communityforum.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeBoardRepository extends JpaRepository<Board, Long>{

}
