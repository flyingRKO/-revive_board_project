package com.example.communityforum.service;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.NoticeBoard;
import com.example.communityforum.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void boardWrite(Board board) {
       boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }//안씀

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
