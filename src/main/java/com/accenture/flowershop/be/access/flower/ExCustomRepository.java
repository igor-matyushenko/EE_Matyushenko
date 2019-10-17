package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.dom4j.tree.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface ExCustomRepository<T extends AbstractEntity, P extends EntityPathBase<T>, ID extends Serializable>
        extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T>, QuerydslBinderCustomizer<P> {
    @Override
    default void customize(QuerydslBindings bindings, P root) {
    }

}