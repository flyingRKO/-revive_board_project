package com.example.communityforum.controller;

import com.example.communityforum.domain.MemberCustom;
import com.example.communityforum.domain.constants.BoardType;
import com.example.communityforum.domain.constants.FormStatus;
import com.example.communityforum.domain.constants.SearchType;
import com.example.communityforum.dto.request.BoardRequest;
import com.example.communityforum.dto.response.BoardResponse;
import com.example.communityforum.dto.response.BoardWithCommentResponse;
import com.example.communityforum.service.BoardService;
import com.example.communityforum.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@Controller
public class BoardController {

    private final BoardService boardService;
    private final PaginationService paginationService;

    @GetMapping
    public String boards(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 20, sort = "registeredDate", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map) {

        Page<BoardResponse> boards = boardService.searchBoards(BoardType.FORUM, searchType, searchValue, pageable).map(BoardResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(
                pageable.getPageNumber(),
                boards.getTotalPages()
        );
        map.addAttribute("boards", boards);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());

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
    public String postNewBoard(
            @AuthenticationPrincipal MemberCustom memberCustom,
            @Valid BoardRequest boardRequest
    ) {

        boardService.saveBoard(boardRequest.toDto(memberCustom.toDto()));

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
    public String updateBoard(
            @PathVariable Long boardId,
            @AuthenticationPrincipal MemberCustom memberCustom,
            BoardRequest boardRequest
    ){

        boardService.updateBoard(boardId, boardRequest.toDto(memberCustom.toDto()));

        return "redirect:/boards/" + boardId;
    }


    @PostMapping("/{boardId}/delete")
    public String deleteBoard(
            @PathVariable Long boardId,
            @AuthenticationPrincipal MemberCustom memberCustom
    ){

        boardService.deleteBoard(boardId, memberCustom.getUsername());

        return "redirect:/boards";
    }

}
