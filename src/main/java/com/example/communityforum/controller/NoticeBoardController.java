package com.example.communityforum.controller;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.constant.BoardType;
import com.example.communityforum.service.BoardService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Builder
//@RequestMapping("/boards")
public class NoticeBoardController {
    private final BoardService boardService;

    @GetMapping("/board/noticeBoardForm")
    public String noticeBoardWriteForm() {
        System.out.println("호출@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "board/noticeBoardForm";
    }

    @PostMapping("/board/noticeBoardFormProcess")
    public String insertNoticeBoard(Board board) {//save는 id가 있으면 update,id가 없으면 insert
        System.out.println("이것은 아이디야@@@@@@@@@@@@@@@@@@@@@" +board.getId());
//        if (board.getId()!=null) {
//            String title = board.getTitle();
//            String content = board.getContent();
//            board = boardService.findById(board.getId());
//            board.setTitle(title);
//            board.setContent(content);
//        }
            board.setBoardType(BoardType.valueOf("NOTICE"));
            boardService.boardWrite(board);


        return "redirect:noticeBoardList";
    }


    @GetMapping("/board/noticeBoardList")
    public String noticeBoardList(Model model) {
        model.addAttribute("boardList",boardService.findAll());
        return "/board/noticeBoardList";
    }

    @GetMapping("/board/noticeBoardDetail/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("board",boardService.findById(id));
        return "/board/noticeBoardDetail";
    }

    @GetMapping("/board/noticeBoardDelete/{id}")
    public String deleteById(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/board/noticeBoardList";
    }

    @GetMapping("/board/noticeBoardFormEdit/{id}")
    public String updateNoticeBoard(@PathVariable Long id ,Model model) {
        model.addAttribute("board",boardService.findById(id));
        return "board/noticeBoardFormEdit";
    }

}
