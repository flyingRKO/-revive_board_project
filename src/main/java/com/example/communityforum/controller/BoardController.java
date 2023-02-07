package com.example.communityforum.controller;

import com.example.communityforum.domain.Board;
import com.example.communityforum.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/home")
    String home() {
        return "home";
    }

    @GetMapping("/board/boardForm")
    public String BoardWriteForm() {
        System.out.println("호출@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "/board/boardForm";
    }

    @PostMapping("/board/boardFormProcess")
    public String insertBoard(Board board) {
        System.out.println("제목: "+ board.getTitle());
        boardService.boardWrite(board);
        return "noticeBoardList";
    }
}
