package com.example.communityforum.repository;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.QBoard;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BoardRepository extends
        JpaRepository<Board, Long>,
        QuerydslPredicateExecutor<Board>,
        QuerydslBinderCustomizer<QBoard> {

    Page<Board> findByTitleContaining(String title, Pageable pageable);
    Page<Board> findByContentContaining(String content, Pageable pageable);
    Page<Board> findByMember_MemberIdContaining(String memberId, Pageable pageable);
    Page<Board> findByMember_NameContaining(String name, Pageable pageable);

    void deleteByIdAndMember_MemberId(Long boardCommentId, String memberId);

    @Override
    default void customize(QuerydslBindings bindings, QBoard root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.registeredDate);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.registeredDate).first(DateTimeExpression::eq);
    }
}
