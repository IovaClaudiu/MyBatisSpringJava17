package com.iova.mybatis.mapper;

import com.iova.mybatis.dto.ArticleDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {

    @Select("SELECT * FROM ARTICLES WHERE id = #{id}")
    Optional<ArticleDto> getArticle(@Param("id") Long id);

    @Select("SELECT * FROM ARTICLES")
    List<ArticleDto> getArticles();

    @Insert("INSERT INTO ARTICLES(id, title, author) VALUES (#{id}, #{title}, #{author})")
    void insertArticle(ArticleDto article);

    @Delete("DELETE FROM ARTICLES WHERE id = #{id}")
    void deleteArticle(@Param("id") Long id);

    @Update("UPDATE ARTICLES SET title=#{title}, author=#{author} WHERE id=#{id}")
    void updateArticle(ArticleDto article);
}
