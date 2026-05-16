package com.example.firstproject_b.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor; // 👈 롬복 임포트
import lombok.NoArgsConstructor; // 👈 롬복 기본 생성자 임포트
import lombok.ToString;         // 👈 롬복 임포트
import lombok.Getter; // 👈 1. Getter 임포트 추가

@AllArgsConstructor // ❶ Article() 생성자를 대체하는 어노테이션 추가
@NoArgsConstructor  // ❶ 기본 생성자 자동 추가 어노테이션
@ToString           // ❷ toString() 메서드를 대체하는 어노테이션 추가
@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // ✂️ 기존에 있던 생성자와 toString() 코드는 롬복이 대신해주므로 모두 삭제했습니다!

}