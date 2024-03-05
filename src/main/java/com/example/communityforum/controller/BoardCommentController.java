package com.example.communityforum.controller;

import com.example.communityforum.domain.MemberCustom;
import com.example.communityforum.dto.request.BoardCommentRequest;
import com.example.communityforum.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping("/new")
    public String postNewBoardComment(
            @AuthenticationPrincipal MemberCustom memberCustom,
            @Valid BoardCommentRequest boardCommentRequest
    ){
        boardCommentService.saveBoardComment(boardCommentRequest.toDto(memberCustom.toDto()));

        return "redirect:/boards/" + boardCommentRequest.getBoardId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteBoardComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal MemberCustom memberCustom,
            Long boardId
            ) {
        boardCommentService.deleteBoardComment(commentId, memberCustom.getUsername());

        return "redirect:/boards/" + boardId;
    }
}
