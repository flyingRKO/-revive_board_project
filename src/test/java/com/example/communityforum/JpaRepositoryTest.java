package com.example.communityforum;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.constant.BoardType;
import com.example.communityforum.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class JpaRepositoryTest {
    private final BoardRepository boardRepository;

    public JpaRepositoryTest(@Autowired BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long preCnt = boardRepository.count();

        // When
        Board savedBoard = boardRepository.save(Board.of(BoardType.NOTICE, "new title", "test content"));

        // Then
        assertThat(boardRepository.count()).isEqualTo(preCnt + 1);

    }
}
