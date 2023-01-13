package com.iova.mybatis.controller;

import com.iova.mybatis.dto.Article;
import com.iova.mybatis.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.iova.mybatis.config.ArticleRoutes.ARTICLES_ROUTE;
import static com.iova.mybatis.config.ArticleRoutes.ARTICLE_ROUTE;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping(path = ARTICLES_ROUTE)
    public ResponseEntity<List<Article>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @GetMapping(path = ARTICLE_ROUTE)
    public ResponseEntity<Article> getArticle(@PathVariable final Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @PostMapping(path = ARTICLES_ROUTE)
    public ResponseEntity<Article> createArticle(@RequestBody @Valid final Article article) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(article));
    }

    @PutMapping(path = ARTICLES_ROUTE)
    public ResponseEntity<Article> updateArticle(@RequestBody @Valid final Article article) {
        return ResponseEntity.ok(articleService.updateArticle(article));
    }

    @DeleteMapping(path = ARTICLE_ROUTE)
    public ResponseEntity<?> deleteArticle(@PathVariable final Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
