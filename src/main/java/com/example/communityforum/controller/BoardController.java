package com.example.communityforum.controller;

import com.example.communityforum.domain.constants.FormStatus;
import com.example.communityforum.domain.constants.MemberRole;
import com.example.communityforum.domain.constants.SearchType;
import com.example.communityforum.dto.MemberDto;
import com.example.communityforum.dto.request.BoardRequest;
import com.example.communityforum.dto.response.BoardResponse;
import com.example.communityforum.dto.response.BoardWithCommentResponse;
import com.example.communityforum.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/boards")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boards(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "registeredDate", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map) {
        map.addAttribute("boards", boardService.searchBoards(searchType, searchValue, pageable).map(BoardResponse::from));
        return "boards/index";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable Long boardId, ModelMap map){
        BoardWithCommentResponse board = BoardWithCommentResponse.from(boardService.getBoardWithComments(boardId));
        map.addAttribute("board", board);
        map.addAttribute("boardComments", board.getBoardCommentResponses());

        return "boards/detail";

    }

    @GetMapping("/form")
    public String boardForm(ModelMap map){
        map.addAttribute("formStatus", FormStatus.CREATE);

        return "boards/form";
    }

    @PostMapping("/form")
    public String postNewBoard(BoardRequest boardRequest) {
        // 인증 정보를 넣어줘야 함
        boardService.saveBoard(boardRequest.toDto(MemberDto.of(
                "rko", "111111", "이재훈", "01011114444", MemberRole.USER, true
        )));

        return "redirect:/boards";
    }

    @GetMapping("/{boardId}/form")
    public String updateBoardForm(@PathVariable Long boardId, ModelMap map){
        BoardResponse board = BoardResponse.from(boardService.getBoard(boardId));

        map.addAttribute("board", board);
        map.addAttribute("formStatus", FormStatus.UPDATE);

        return "boards/form";
    }

    @PostMapping("/{boardId}/form")
    public String updateBoard(@PathVariable Long boardId, BoardRequest boardRequest){
        // 인증 정보 넣어줘야 함
        boardService.updateBoard(boardId, boardRequest.toDto(MemberDto.of(
                "rko", "111111", "이재훈", "01011114444", MemberRole.USER, true
        )));

        return "redirect:/boards/" + boardId;
    }


    @PostMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long boardId){
        // 인증 정보 넣어줘야 함
        boardService.deleteBoard(boardId);

        return "redirect:/boards";
    }

}
