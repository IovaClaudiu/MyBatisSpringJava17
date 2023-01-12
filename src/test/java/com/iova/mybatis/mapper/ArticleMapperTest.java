package com.iova.mybatis.mapper;

import com.iova.mybatis.config.PersistenceConfig;
import com.iova.mybatis.dto.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceConfig.class)
class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    @DisplayName("Get the first and the only article from h2 using mybatis")
    @Order(1)
    public void getArticle() {
        Article article = articleMapper.getArticle(1L);

        assertThat(article).isNotNull();
        assertThat(article.getAuthor()).isEqualTo("Iova");
        assertThat(article.getTitle()).isEqualTo("MyBatis Example");

    }

    @Test
    @DisplayName("Get the articles from h2 using mybatis")
    @Order(2)
    public void getArticles() {
        List<Article> articles = articleMapper.getArticles();

        assertThat(articles).isNotNull();
        assertThat(articles).hasSize(1);
    }

    @Test
    @DisplayName("Insert a new article in h2 using mybatis")
    @Order(3)
    public void insertArticle() {
        Article article = new Article();
        article.setId(2L);
        article.setTitle("My Title");
        article.setAuthor("Iova Claudiu");

        articleMapper.insertArticle(article);

        List<Article> articles = articleMapper.getArticles();
        assertThat(articles).hasSize(2);

        articles.stream()
                .filter(art -> art.getId() == 2L)
                .findFirst()
                .ifPresentOrElse(art -> {
                    assertThat(art.getAuthor()).isEqualTo("Iova Claudiu");
                    assertThat(art.getTitle()).isEqualTo("My Title");
                    assertThat(art.getId()).isEqualTo(2L);
                }, () -> fail("no entity with id 2 has been found"));
    }

    @Test
    @Order(4)
    @DisplayName("Delete the new entry previously inserted in the test, using mybatis")
    public void deleteArticle() {
        articleMapper.deleteArticle(2L);

        List<Article> articles = articleMapper.getArticles();
        assertThat(articles).hasSize(1);
    }

    @Test
    @Order(5)
    @DisplayName("Update the first entity from the h2 database, using mybatis")
    public void updateArticle() {
        Article updateArticle = new Article();
        updateArticle.setId(1L);
        updateArticle.setAuthor("New Author");
        updateArticle.setTitle("New Title");

        articleMapper.updateArticle(updateArticle);

        Article article = articleMapper.getArticle(1L);

        assertThat(article).isNotNull();
        assertThat(article.getAuthor()).isEqualTo("New Author");
        assertThat(article.getTitle()).isEqualTo("New Title");
    }
}