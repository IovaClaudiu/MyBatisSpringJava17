package com.iova.mybatis.mapper;

import com.iova.mybatis.config.PersistencePostgresqlConfig;
import com.iova.mybatis.dto.ArticleDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = PersistenceH2Config.class)
@ContextConfiguration(classes = PersistencePostgresqlConfig.class)
@Sql(value = "classpath:reset.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    @DisplayName("Get the first and the only article from h2 using mybatis")
    @Order(1)
    public void getArticle() {
        final ArticleDto article = articleMapper.getArticle(1L).orElseGet(() -> fail("No article with id 1 was found"));

        assertThat(article).isNotNull();
        assertThat(article.getAuthor()).isEqualTo("Iova");
        assertThat(article.getTitle()).isEqualTo("MyBatis Example");

    }

    @Test
    @DisplayName("Get the articles from h2 using mybatis")
    @Order(2)
    public void getArticles() {
        final List<ArticleDto> articles = articleMapper.getArticles();

        assertThat(articles).isNotNull();
        assertThat(articles).hasSize(1);
    }

    @Test
    @DisplayName("Insert a new article in h2 using mybatis")
    @Order(3)
    public void insertArticle() {
        final ArticleDto article = ArticleDto.builder()
                .id(2L)
                .title("My Title")
                .author("Iova Claudiu")
                .build();

        articleMapper.insertArticle(article);

        final List<ArticleDto> articles = articleMapper.getArticles();
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

        final List<ArticleDto> articles = articleMapper.getArticles();
        assertThat(articles).hasSize(1);
    }

    @Test
    @Order(5)
    @DisplayName("Update the first entity from the h2 database, using mybatis")
    public void updateArticle() {
        final ArticleDto updateArticle = ArticleDto.builder()
                .id(1L)
                .author("New Author")
                .title("New Title")
                .build();

        articleMapper.updateArticle(updateArticle);

        final ArticleDto article = articleMapper.getArticle(1L).orElseGet(() -> fail("No article with id 1 was found"));

        assertThat(article).isNotNull();
        assertThat(article.getAuthor()).isEqualTo("New Author");
        assertThat(article.getTitle()).isEqualTo("New Title");
    }
}