package com.example.communityforum.controller;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.MemberCustom;
import com.example.communityforum.domain.constants.BoardType;
import com.example.communityforum.repository.MemberRepository;
import com.example.communityforum.service.NoticeBoardService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Builder
//@RequestMapping("/boards")
public class NoticeBoardController {
    private final NoticeBoardService boardService;
    private final MemberRepository memberRepository;
    @GetMapping("/board/noticeBoardForm")
    public String noticeBoardWriteForm() {

        return "boards/noticeBoardForm";
    }

    @PostMapping("/board/noticeBoardFormProcess")
    public String insertNoticeBoard(Board board) {//save는 id가 있으면 update,id가 없으면 insert
        Object principal            = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberCustom memberCustom   = (MemberCustom)principal;
        Member member               = memberRepository.findByMemberId(memberCustom.getUsername()).get();

        board.setMember(member);
        board.setBoardType(BoardType.valueOf("NOTICE"));
        boardService.boardWrite(board);

        return "redirect:noticeBoardList";
    }


    @GetMapping("/board/noticeBoardList")
    public String noticeBoardList(Model model) {
        model.addAttribute("boardList", boardService.findAll());
        return "/boards/noticeBoardList";
    }


    @GetMapping("/board/noticeBoardDetail/{id}")
    public String findById(@PathVariable Long id, Model model) {
//        BoardCommentResponse board = BoardCommentResponse.from(boardService.getBoardComments(boardId));
//        map.addAttribute("board", board);
//        map.addAttribute("boardComments", board.getBoardCommentResponses());
        model.addAttribute("board", boardService.findById(id));
        return "/boards/noticeBoardDetail";
    }

    @GetMapping("/board/noticeBoardDelete/{id}")
    public String deleteById(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/boards/noticeBoardList";
    }

//    @PostMapping("/{boardId}/form")
//    public String updateBoard(
//            @PathVariable Long boardId,
//            @AuthenticationPrincipal MemberCustom memberCustom,
//            BoardRequest boardRequest
//    ){
//
//        boardService.updateBoard(boardId, boardRequest.toDto(memberCustom.toDto()));
//
//        return "redirect:/boards/{id}" ;
//    }

    @GetMapping("/board/noticeBoardFormEdit/{id}")
    public String updateNoticeBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "boards/noticeBoardFormEdit";
    }

//    @GetMapping("/board/noticeBoardDetail/{id}")
//    public String findById(@PathVariable Board id, Model model){
//        model.addAttribute("commentList",boardService.findById(id.getId()));
//        return "redirect:/board/noticeBoardDetail/{id}";
//    }
}
