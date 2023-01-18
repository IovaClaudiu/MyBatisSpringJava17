package com.iova.mybatis.service;

import com.iova.mybatis.dto.ArticleDto;
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

    public List<ArticleDto> getArticles() {
        return articleMapper.getArticles();
    }

    public ArticleDto getArticle(final Long id) {
        final Optional<ArticleDto> article = articleMapper.getArticle(id);
        return article.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Failed to find an article with id: " + id));
    }

    public ArticleDto createArticle(final ArticleDto article) {
        articleMapper.insertArticle(article);
        return getArticle(article.getId());
    }

    public ArticleDto updateArticle(final ArticleDto article) {
        final ArticleDto result = getArticle(article.getId());
        final ArticleDto updateArticle = ArticleDto.builder()
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
