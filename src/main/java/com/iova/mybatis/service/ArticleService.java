package com.iova.mybatis.service;

import com.iova.mybatis.dto.ArticleDto;
import com.iova.mybatis.entity.ArticleEntity;
import com.iova.mybatis.exception.BusinessException;
import com.iova.mybatis.mapper.MyBatisMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final MyBatisMapper articleMapper;

    public List<ArticleDto> getArticles() {
        return articleMapper.getArticles();
    }

    public ArticleDto getArticle(final Long id) {
        final Optional<ArticleDto> article = articleMapper.getArticle(id);
        return article.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Failed to find an article with id: " + id));
    }

    public ArticleDto createArticle(final ArticleEntity article) {
        articleMapper.insertArticle(article);
        return getArticle(article.getId());
    }

    public void updateArticle(final ArticleEntity article) {
        final ArticleDto result = getArticle(article.getId());
        final ArticleEntity updated = new ArticleEntity();
        updated.setId(result.getId());
        updated.setTitle(article.getTitle() != null ? article.getTitle() : result.getTitle());
        updated.setDescription(article.getDescription() != null ? article.getDescription() : result.getDescription());
        updated.setAuthorId(article.getAuthorId() != null ? article.getAuthorId() : result.getAuthor().getId());
        articleMapper.updateArticle(updated);
    }

    public void deleteArticle(final Long id) {
        articleMapper.deleteArticle(id);
    }
}
