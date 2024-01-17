package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository aricleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        //System.out.println(form.toString());
        //1.DTO를 엔티티로 변환
        Article article=form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = aricleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "articles/create";
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id="+id);
        //1.id를 조회해 데이터 가져오기
        Article articleEntity =aricleRepository.findById(id).orElse(null);
        Optional<Article> article = aricleRepository.findById(id);
        //2. 모델에 데이터 등록하기
        model.addAttribute("aricle",articleEntity);
        //3. 뷰 페이지 반환하기
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = aricleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }
}
