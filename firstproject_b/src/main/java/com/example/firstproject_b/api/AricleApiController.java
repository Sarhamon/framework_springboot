package com.example.firstproject_b.api;
import com.example.firstproject_b.dto.ArticleForm;
import com.example.firstproject_b.entity.Article;
import com.example.firstproject_b.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class AricleApiController {
    @Autowired // 게시글 리파지터리 주입
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles") // URL 요청 접수
    public List<Article> index() { // index() 메서드 정의
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    // POST
    @PostMapping("/api/articles") // 1. URL 요청 접수
    public Article create(@RequestBody ArticleForm dto) { // 2. create() 메서드 정의
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    // PATCH
    // PATCH
    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) { // 🟢 반환형을 ResponseEntity로 수정!

        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기 (대상이 없거나 id가 다를 때)
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article); // 🟢 책 318p(다음 장)에 나올 핵심 로직입니다! (미리 넣어두시면 에러 안 납니다)
        Article updated = articleRepository.save(target); // article 대신 target을 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE

}
