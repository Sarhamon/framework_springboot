package com.example.firstproject_b.dto;

import com.example.firstproject_b.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id; // 🟢 215p: id 필드 추가
    private String title;
    private String content;

    public Article toEntity() {
        // 🟢 216p: null 자리에 id를 넣도록 수정
        return new Article(id, title, content);
    }
}