package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.entity.Article;
import com.example.InstaPay_Travel_Tours.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "http://localhost:3000")  // Adjust for your frontend URL if needed
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // Existing method to get articles
    @GetMapping
    public List<Article> getArticles() {
        return articleService.getAllArticles();
    }

    // Method to add a new article
    @PostMapping
    public Article addArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }
}
