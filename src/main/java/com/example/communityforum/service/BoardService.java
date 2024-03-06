package com.example.communityforum.service;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.BoardType;
import com.example.communityforum.domain.constants.SearchType;
import com.example.communityforum.dto.BoardDto;
import com.example.communityforum.dto.BoardWithCommentsDto;
import com.example.communityforum.repository.BoardRepository;
import com.example.communityforum.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<BoardDto> searchBoards(BoardType boardType, SearchType searchType, String searchKeyword, Pageable pageable){
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return boardRepository.findByBoardType(boardType, pageable).map(BoardDto::from);
        }
        return switch(searchType){
            case TITLE -> boardRepository.findByBoardTypeAndTitleContaining(boardType, searchKeyword,pageable).map(BoardDto::from);
            case CONTENT -> boardRepository.findByBoardTypeAndContentContaining(boardType, searchKeyword,pageable).map(BoardDto::from);
            case ID ->boardRepository.findByBoardTypeAndMember_MemberIdContaining(boardType, searchKeyword,pageable).map(BoardDto::from);
         };

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
        Member member = memberRepository.getReferenceById(dto.memberDto().memberId());
        if (dto.boardType().equals(BoardType.FORUM)) {
            boardRepository.save(dto.toForumEntity(member));
        }
    }

    public void updateBoard(Long boardId, BoardDto dto){
        try {
            Board board = boardRepository.getReferenceById(boardId);

            Member member = memberRepository.getReferenceById(dto.memberDto().memberId());

            if (board.getMember().equals(member)){
                if (dto.title() != null) { board.setTitle(dto.title());}
                if (dto.content() != null) { board.setContent(dto.content());}
            }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 수정에 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }

    }

    public void deleteBoard(long boardId, String memberId){
        boardRepository.deleteByIdAndMember_MemberId(boardId, memberId);
    }

    public long getBoardCount() {
        return boardRepository.count();
    }

}
