package com.iova.mybatis.mapper;

import com.iova.mybatis.dto.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("SELECT * FROM ARTICLES WHERE id = #{id}")
    Article getArticle(@Param("id") Long id);

    @Select("SELECT * FROM ARTICLES")
    List<Article> getArticles();

    @Insert("INSERT INTO ARTICLES(id, title, author) VALUES (#{id}, #{title}, #{author})")
    void insertArticle(Article article);

    @Delete("DELETE FROM ARTICLES WHERE id = #{id}")
    void deleteArticle(@Param("id") Long id);

    @Update("UPDATE ARTICLES SET title=#{title}, author=#{author} WHERE id=#{id}")
    void updateArticle(Article article);
}
