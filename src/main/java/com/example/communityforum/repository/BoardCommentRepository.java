package com.example.communityforum.repository;

import com.example.communityforum.domain.BoardComment;
import com.example.communityforum.domain.QBoardComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BoardCommentRepository extends
        JpaRepository<BoardComment, Long>,
        QuerydslPredicateExecutor<BoardComment>,
        QuerydslBinderCustomizer<QBoardComment>
{
    @Override
    default void customize(QuerydslBindings bindings, QBoardComment root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.registeredDate); // 추후 user 정보도 필요
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.registeredDate).first(DateTimeExpression::eq);
    }
}
