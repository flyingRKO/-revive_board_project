package com.example.communityforum.service;

import com.example.communityforum.domain.BoardComment;
import com.example.communityforum.repository.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeBoardCommentService {
    private final BoardCommentRepository boardCommentRepository;

    public void boardCommentWrite(BoardComment boardComment){
        boardCommentRepository.save(boardComment);
    }
    public List<BoardComment> findAll() {
        return boardCommentRepository.findAll();
    }

    public BoardComment findById(Long id) {
        return boardCommentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        boardCommentRepository.deleteById(id);
    }
}

