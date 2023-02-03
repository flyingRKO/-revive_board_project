package com.example.communityforum.service;

import com.example.communityforum.domain.Board;
import com.example.communityforum.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void boardWrite(Board board){
        boardRepository.save(board);
    }
}
