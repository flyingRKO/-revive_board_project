package com.example.communityforum.controller;

import com.example.communityforum.service.BoardService;
import com.example.communityforum.service.NoticeBoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NoticeBoardCommentController {
    private final NoticeBoardCommentService noticeBoardCommentService;
    private final BoardService boardService;

//    @PostMapping("board/noticeBoardDetail/{id}")
//    public String insertNoticeBoardComment(@PathVariable Board id, BoardComment boardComment){
//        System.out.println("insertNoticeBoardComment 호출@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        System.out.println("댓글내용 @@@@@@@@@@@@@@@@@@  :"+boardComment);
//        boardComment.setBoard(id);
//        noticeBoardCommentService.boardCommentWrite(boardComment);
//        //comment가 입력되면 list호출
//
//        return "redirect:/board/noticeBoardDetail/{id}";
//    }
//    @GetMapping("/board/noticeBoardDetail/{id}")
//    public String findById(@PathVariable Board id, Model model){
//        model.addAttribute("commentList",Service.findById(id.getId()));
//      return "redirect:/board/noticeBoardDetail/{id}";
//    }

}
