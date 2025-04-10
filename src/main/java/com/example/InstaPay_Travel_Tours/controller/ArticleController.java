package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.entity.Article;
import com.example.InstaPay_Travel_Tours.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping
    public Article addArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }
}
