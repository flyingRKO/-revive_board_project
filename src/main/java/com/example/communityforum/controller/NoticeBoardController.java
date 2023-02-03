package com.example.communityforum.controller;

import com.example.communityforum.domain.Board;
import com.example.communityforum.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NoticeBoardController {
    private final BoardService boardService;

    @GetMapping("/board/noticeBoardForm")
    public String noticeBoardWriteForm() {
        System.out.println("호출@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "board/noticeBoardForm";
    }

    @PostMapping("/board/noticeBoardFormProcess")
    public String insertNoticeBoard(Board board) {
        System.out.println("제목: "+ board.getTitle());
        boardService.boardWrite(board);
        return "/board/noticeBoardList";
    }
}
