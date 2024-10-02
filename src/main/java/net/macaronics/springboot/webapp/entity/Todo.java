package net.macaronics.springboot.webapp.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "todos")  // 테이블 이름을 "todos"로 설정
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString(exclude = "user")  // 순환 참조 방지
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID 자동 생성 전략을 사용 (Auto Increment)
    @Column(name = "todo_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)  // CascadeType.ALL 제거
    @JoinColumn(name = "user_id")  // 외래키로 "user_id" 컬럼을 사용
    private User user;
    
    private String description;  // 할 일 설명
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // 날짜 형식을 "yyyy-MM-dd"로 설정
    private LocalDate targetDate;  // 목표 날짜
    
    private boolean done;  // 완료 여부
      
}




