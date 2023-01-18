package com.iova.mybatis.controller;

import com.iova.mybatis.dto.ArticleDto;
import com.iova.mybatis.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.iova.mybatis.config.Routes.ARTICLES_ROUTE;
import static com.iova.mybatis.config.Routes.ARTICLE_ROUTE;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping(path = ARTICLES_ROUTE)
    public ResponseEntity<List<ArticleDto>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @GetMapping(path = ARTICLE_ROUTE)
    public ResponseEntity<ArticleDto> getArticle(@PathVariable final Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @PostMapping(path = ARTICLES_ROUTE)
    public ResponseEntity<ArticleDto> createArticle(@RequestBody @Valid final ArticleDto article) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(article));
    }

    @PutMapping(path = ARTICLES_ROUTE)
    public ResponseEntity<ArticleDto> updateArticle(@RequestBody @Valid final ArticleDto article) {
        return ResponseEntity.ok(articleService.updateArticle(article));
    }

    @DeleteMapping(path = ARTICLE_ROUTE)
    public ResponseEntity<?> deleteArticle(@PathVariable final Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
