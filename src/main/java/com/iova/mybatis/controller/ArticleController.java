package com.iova.mybatis.controller;

import com.iova.mybatis.config.ArticleRoutes;
import com.iova.mybatis.dto.Article;
import com.iova.mybatis.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping(path = ArticleRoutes.ARTICLES_ROUTE)
    public ResponseEntity<List<Article>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @GetMapping(path = ArticleRoutes.ARTICLE_ROUTE)
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @PostMapping(path = ArticleRoutes.ARTICLES_ROUTE)
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(article));
    }
}
