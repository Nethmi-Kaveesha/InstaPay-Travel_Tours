package com.example.InstaPay_Travel_Tours.repo;


import com.example.InstaPay_Travel_Tours.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
