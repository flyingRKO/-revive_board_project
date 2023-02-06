package com.example.communityforum.repository;

import com.example.communityforum.config.JpaConfig;
import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.UserAccount;
import com.example.communityforum.domain.constant.BoardType;
import com.example.communityforum.domain.constant.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class JpaRepositoryTest {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(
            @Autowired BoardRepository boardRepository,
            @Autowired BoardCommentRepository boardCommentRepository,
            @Autowired UserAccountRepository userAccountRepository
    ) {
        this.boardRepository = boardRepository;
        this.boardCommentRepository = boardCommentRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given


        // When
        List<Board> boards = boardRepository.findAll();

        // Then
        assertThat(boards)
                .isNotNull()
                .hasSize(2);

    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long preCnt = boardRepository.count();
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("rko","pw","lee","01011112222",Role.GENERAL,true));
        Board board = Board.of(userAccount, BoardType.FORUM, "new board", "new content");

        // When
        boardRepository.save(board);


        // Then
        assertThat(boardRepository.count()).isEqualTo(preCnt + 1);


    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Board board = boardRepository.findById(1L).orElseThrow();
        String updatedTitle = "이거 되나";
        board.setTitle(updatedTitle);

        // When
        Board savedBoard = boardRepository.saveAndFlush(board);

        // Then
        assertThat(savedBoard).hasFieldOrPropertyWithValue("title", updatedTitle);


    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Board board = boardRepository.findById(1L).orElseThrow();
        long preBoardCnt = boardRepository.count();
        long preBoardCommentCnt = boardCommentRepository.count();
        int deletedCommentSize = board.getBoardComments().size();


        // When
        boardRepository.delete(board);

        // Then
        assertThat(boardRepository.count()).isEqualTo(preBoardCnt - 1);
        assertThat(boardCommentRepository.count()).isEqualTo(preBoardCommentCnt - 1);


    }


}