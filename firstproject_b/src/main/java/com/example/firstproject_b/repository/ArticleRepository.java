package com.example.firstproject_b.repository;

import com.example.firstproject_b.entity.Article;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList; // 👈 ArrayList를 위한 임포트 추가!

public interface ArticleRepository extends CrudRepository<Article, Long>{
    @Override
    ArrayList<Article> findAll(); // Iterable을 ArrayList로 수정해서 오버라이딩

}
