package com.example.communityforum.service;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.UserAccount;
import com.example.communityforum.domain.constant.SearchType;
import com.example.communityforum.dto.BoardDto;
import com.example.communityforum.dto.BoardWithCommentsDto;
import com.example.communityforum.repository.BoardRepository;
import com.example.communityforum.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityNotFoundException;

import static com.example.communityforum.domain.constant.SearchType.*;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    private final UserAccountRepository userAccountRepository;

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<BoardDto> searchBoards(SearchType searchType, String searchKeyword, Pageable pageable){
        Page<BoardDto> dtos = null;
        if (searchKeyword == null || searchKeyword.isBlank()){
             dtos = boardRepository.findAll(pageable).map(BoardDto::from);
            return boardRepository.findAll(pageable).map(BoardDto::from);
        }
        switch(searchType){
            case TITLE:
                dtos = boardRepository.findByTitleContaining(searchKeyword,pageable).map(BoardDto::from);
                break;
            case CONTENT:
                dtos = boardRepository.findByContentContaining(searchKeyword,pageable).map(BoardDto::from);
                break;
            case ID:
                dtos = boardRepository.findByUserAccount_UserIdContaining(searchKeyword,pageable).map(BoardDto::from);
                break;
            case USER_NAME:
                dtos = boardRepository.findByUserAccount_UserNameContaining(searchKeyword,pageable).map(BoardDto::from);
                break;
        }

        return dtos;
        }


        
        
    
    @Transactional(readOnly = true)
    public BoardWithCommentsDto getBoardWithComments(Long boardId){
        return boardRepository.findById(boardId)
                .map(BoardWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다. - boardId: " + boardId));
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(Long boardId){
        return boardRepository.findById(boardId)
                .map(BoardDto::from)
                .orElseThrow(()-> new EntityNotFoundException("게시글이 없습니다 - boardId: " + boardId));
    }


    public void saveBoard(BoardDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());
        boardRepository.save(dto.toEntity(userAccount));
    }

    public void updateBoard(Long boardId, BoardDto dto){
        try {
            Board board = boardRepository.getReferenceById(boardId);
            if (dto.getTitle() != null) { board.setTitle(dto.getTitle());}
            if (dto.getContent() != null) { board.setContent(dto.getContent());}
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다 - dto: {}", dto);
        }



    }

    public void deleteBoard(long boardId){
        boardRepository.deleteById(boardId);
    }



}
