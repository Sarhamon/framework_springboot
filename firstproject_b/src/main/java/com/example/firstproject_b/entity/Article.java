package com.example.firstproject_b.entity;

import jakarta.persistence.*;
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

    // 🟢 PATCH 요청 시 일부 데이터만 갱신하기 위한 메서드
    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }

    // ✂️ 기존에 있던 생성자와 toString() 코드는 롬복이 대신해주므로 모두 삭제했습니다!

}