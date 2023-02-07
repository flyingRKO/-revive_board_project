package com.example.communityforum.service;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.BoardComment;
import com.example.communityforum.domain.UserAccount;
import com.example.communityforum.dto.BoardCommentDto;
import com.example.communityforum.repository.BoardCommentRepository;
import com.example.communityforum.repository.BoardRepository;
import com.example.communityforum.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BoardCommentService {
    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public void saveBoardComment(BoardCommentDto dto) {
        try{
            Board board = boardRepository.getReferenceById(dto.getBoardId());

            UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());

            boardCommentRepository.save(dto.toEntity(board, userAccount));
        } catch (EntityNotFoundException e){
            log.warn("댓글 저장 실패. 댓글 작성에 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }

    public void updateBoardComment(BoardCommentDto dto) {
        try{
            BoardComment boardComment = boardCommentRepository.getReferenceById(dto.getId());
            if (dto.getContent() != null) {
                boardComment.setContent(dto.getContent());
            }
        } catch (EntityNotFoundException e){
            log.warn("댓글 업데이트 실패. 댓글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    public void deleteBoardComment(Long boardCommentId) {
        boardCommentRepository.deleteById(boardCommentId);
    }
}
