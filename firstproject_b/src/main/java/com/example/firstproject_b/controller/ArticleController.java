package com.example.firstproject_b.controller;

import com.example.firstproject_b.repository.ArticleRepository;
import com.example.firstproject_b.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.firstproject_b.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import java.util.List; // 👈 여러 개의 데이터를 담기 위한 List 임포트 추가!
import java.util.ArrayList;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    // 🟢 단일 조회 메서드
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);
        log.info("show:"+articleEntity.toString());
        return "articles/show";
    }


    @GetMapping("/articles")
    public String index(Model model) {


        ArrayList<Article> articleEntityList = articleRepository.findAll();


        model.addAttribute("articleList", articleEntityList);
        log.info("show:"+articleEntityList.toString());

        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);
        return  "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터 값을 갱신하기
        if (target != null) {
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId(); // (이 부분은 다음 장에서 완벽하게 채울 예정입니다!)
    }
    // 🟢 [8장] 삭제 요청을 받는 메서드
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) { // 🟢 1. 파라미터 추가!
        log.info("삭제 요청이 들어왔습니다!!");

        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상 엔티티 삭제하기
        if (target != null) {
            articleRepository.delete(target); // 진짜로 DB에서 삭제!

            // 🟢 2. 삭제 완료 메시지 남기기 (일회성 메시지)
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }

        // 3. 결과 페이지(목록)로 리다이렉트하기
        return "redirect:/articles";

    }
}