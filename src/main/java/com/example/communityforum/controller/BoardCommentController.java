package com.example.communityforum.controller;

import com.example.communityforum.dto.UserAccountDto;
import com.example.communityforum.dto.request.BoardCommentRequest;
import com.example.communityforum.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping("/new")
    public String postNewBoardComment(BoardCommentRequest boardCommentRequest){
        boardCommentService.saveBoardComment(boardCommentRequest.toDto(UserAccountDto.of(
                "rko", "1234", null, null, null, true
        )));

        return "redirect:/boards/" + boardCommentRequest.getBoardId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteBoardComment(@PathVariable Long commentId, Long boardId){
        boardCommentService.deleteBoardComment(commentId);

        return "redirect:/boards/" + boardId;
    }
}
