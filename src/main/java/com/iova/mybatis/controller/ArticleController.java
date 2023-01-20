package com.iova.mybatis.controller;

import com.iova.mybatis.dto.ArticleDto;
import com.iova.mybatis.entity.ArticleEntity;
import com.iova.mybatis.security.role.IsAdmin;
import com.iova.mybatis.security.role.IsUser;
import com.iova.mybatis.security.role.IsViewer;
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
    @IsViewer
    public ResponseEntity<List<ArticleDto>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @GetMapping(path = ARTICLE_ROUTE)
    @IsViewer
    public ResponseEntity<ArticleDto> getArticle(@PathVariable final Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @PostMapping(path = ARTICLES_ROUTE)
    @IsAdmin
    public ResponseEntity<ArticleDto> createArticle(@RequestBody @Valid final ArticleEntity article) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(article));
    }

    @PutMapping(path = ARTICLES_ROUTE)
    @IsUser
    public ResponseEntity<?> updateArticle(@RequestBody @Valid final ArticleEntity article) {
        articleService.updateArticle(article);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = ARTICLE_ROUTE)
    @IsUser
    public ResponseEntity<?> deleteArticle(@PathVariable final Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
