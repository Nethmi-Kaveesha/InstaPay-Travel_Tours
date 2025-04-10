package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.entity.Article;
import com.example.InstaPay_Travel_Tours.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
}
