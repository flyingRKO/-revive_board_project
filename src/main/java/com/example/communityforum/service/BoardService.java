package com.example.communityforum.service;

import com.example.communityforum.domain.NoticeBoard;
import com.example.communityforum.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    public void boardWrite(NoticeBoard noticeBoard) {
       boardRepository.save(noticeBoard);
    }

    public List<NoticeBoard> findAll() {
        return boardRepository.findAll();
    }

    public NoticeBoard findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public NoticeBoard save(NoticeBoard noticeBoard) {
        return boardRepository.save(noticeBoard);
    }//안씀

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
