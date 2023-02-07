package com.example.communityforum.controller;

import com.example.communityforum.domain.BoardComment;
import com.example.communityforum.service.NoticeBoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NoticeBoardCommentController {
    private final NoticeBoardCommentService noticeBoardCommentService;

    @PostMapping("board/noticeBoardDetailComment")
    public String insertNoticeBoardComment(BoardComment boardComment){
        System.out.println("insertNoticeBoardComment 호출@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        noticeBoardCommentService.boardCommentWrite(boardComment);
        return "redirect:noticeBoardDetail";
    }
}
