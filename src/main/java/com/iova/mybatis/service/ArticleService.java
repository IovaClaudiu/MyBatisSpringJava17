package com.iova.mybatis.service;

import com.iova.mybatis.dto.Article;
import com.iova.mybatis.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;

    public List<Article> getArticles() {
        return articleMapper.getArticles();
    }

    public Article getArticle(final Long id) {
        return articleMapper.getArticle(id);
    }

    public Article createArticle(Article article) {
        articleMapper.insertArticle(article);
        return articleMapper.getArticle(article.getId());
    }
}
