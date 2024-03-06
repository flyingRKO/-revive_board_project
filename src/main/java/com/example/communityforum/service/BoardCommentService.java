package com.example.communityforum.service;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.BoardComment;
import com.example.communityforum.domain.Member;
import com.example.communityforum.dto.BoardCommentDto;
import com.example.communityforum.repository.BoardCommentRepository;
import com.example.communityforum.repository.BoardRepository;
import com.example.communityforum.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    public void saveBoardComment(BoardCommentDto dto) {
        try{
            Board board = boardRepository.getReferenceById(dto.boardId());

            Member member = memberRepository.getReferenceById(dto.memberDto().memberId());

            boardCommentRepository.save(dto.toEntity(board, member));
        } catch (EntityNotFoundException e){
            log.warn("댓글 저장 실패. 댓글 작성에 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }

    public void updateBoardComment(BoardCommentDto dto) {
        try{
            BoardComment boardComment = boardCommentRepository.getReferenceById(dto.id());
            if (dto.content() != null) {
                boardComment.setContent(dto.content());
            }
        } catch (EntityNotFoundException e){
            log.warn("댓글 업데이트 실패. 댓글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    public void deleteBoardComment(Long boardCommentId, String memberId) {
        boardCommentRepository.deleteByIdAndMember_MemberId(boardCommentId, memberId);
    }
}
