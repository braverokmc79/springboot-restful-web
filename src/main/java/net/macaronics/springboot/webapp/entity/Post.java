package net.macaronics.springboot.webapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

/**
 * 게시판
 */
@Entity
@Table(name="posts")
public class Post {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	
	
	 @Column(name = "description")
	 private String description;
	 
	 
	 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // 사용자와 다대일 관계 설정 (Lazy Loading)
	 @JoinColumn(name = "user_id")  // 외래키로 "user_id" 컬럼을 사용
	 private  User user;
	 
	 
	 
	 
	
	
}
