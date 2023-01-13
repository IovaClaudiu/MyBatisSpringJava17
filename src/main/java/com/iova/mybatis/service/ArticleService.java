package com.iova.mybatis.service;

import com.iova.mybatis.dto.Article;
import com.iova.mybatis.exception.BusinessException;
import com.iova.mybatis.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;

    public List<Article> getArticles() {
        return articleMapper.getArticles();
    }

    public Article getArticle(final Long id) {
        final Optional<Article> article = articleMapper.getArticle(id);
        return article.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Failed to find an article with id: " + id));
    }

    public Article createArticle(final Article article) {
        articleMapper.insertArticle(article);
        return getArticle(article.getId());
    }

    public Article updateArticle(final Article article) {
        final Article result = getArticle(article.getId());
        final Article updateArticle = Article.builder()
                .id(result.getId())
                .title(article.getTitle())
                .author(article.getAuthor())
                .build();
        articleMapper.updateArticle(updateArticle);
        return updateArticle;
    }

    public void deleteArticle(final Long id) {
        articleMapper.deleteArticle(id);
    }
}
