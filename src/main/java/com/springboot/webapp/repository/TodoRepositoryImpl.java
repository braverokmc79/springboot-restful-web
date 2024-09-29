package com.springboot.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.webapp.dto.TodoResponseDTO;
import com.springboot.webapp.entity.QTodo;
import com.springboot.webapp.entity.QUser;
import com.springboot.webapp.entity.User;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final EntityManager em;  // EntityManager 주입 (JPA Query 실행에 사용)

    @Override
    public Page<TodoResponseDTO> todoListUsername(User user, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);  // QueryDSL 쿼리 빌더
        QTodo todo = QTodo.todo;  // QTodo 엔티티 객체
        QUser qUser = QUser.user;  // QUser 엔티티 객체
    
        // QueryDSL을 사용해 TodoResponseDTO 리스트를 생성
        List<TodoResponseDTO> content = queryFactory.select(Projections.constructor(
                        TodoResponseDTO.class,
                        todo.id,  // Todo ID
                        qUser.id,  // User ID
                        qUser.username,  // User 이름
                        todo.description,  // 할 일 설명
                        todo.targetDate,  // 목표 날짜
                        todo.done  // 완료 여부
                ))
                .from(todo)  // Todo 엔티티로부터
                .join(todo.user, qUser)  // User와 조인
                .where(qUser.eq(user))  // 특정 사용자의 데이터만 가져옴
                .orderBy(todo.id.desc())  // 최신 할 일부터 내림차순 정렬
                .offset(pageable.getOffset())  // 페이지 시작점 설정
                .limit(pageable.getPageSize())  // 페이지 크기 제한
                .fetch();  // 결과를 리스트로 반환
        
        // 번호를 매기는 로직 추가
        long startIndex = pageable.getOffset() + 1;  // 시작 인덱스 설정
        for (int i = 0; i < content.size(); i++) {
            content.get(i).setNum(startIndex + i);  // 각 항목에 번호 부여
        }
        
        // 총 레코드 수를 세는 쿼리
        JPAQuery<Long> countQuery = queryFactory.select(todo.count())
                .from(todo)
                .join(todo.user, qUser)
                .where(qUser.eq(user));
                
        // PageableExecutionUtils를 사용해 페이지 객체 반환
        return PageableExecutionUtils.getPage(content, pageable , countQuery::fetchOne);
    }

}
