package com.iova.mybatis.mapper;

import com.iova.mybatis.dto.ArticleDto;
import com.iova.mybatis.dto.AuthorDto;
import com.iova.mybatis.entity.ArticleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MyBatisMapper {

    @Select(value = "SELECT * FROM authors WHERE id = #{id}")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    AuthorDto selectAuthor(Long id);

    @Select("SELECT * FROM ARTICLES WHERE id = #{id}")
    @Results({
            @Result(property = "author", javaType = AuthorDto.class, column = "author_id",
                    one = @One(select = "selectAuthor"))
    })
    Optional<ArticleDto> getArticle(@Param("id") Long id);

    @Select("SELECT * FROM ARTICLES")
    @Results({
            @Result(property = "author", javaType = AuthorDto.class, column = "author_id",
                    one = @One(select = "selectAuthor"))
    })
    List<ArticleDto> getArticles();


    @Insert("INSERT INTO ARTICLES(title, description, author_id) VALUES (#{title}, #{title}, #{authorId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertArticle(ArticleEntity article);

    @Delete("DELETE FROM ARTICLES WHERE id = #{id}")
    void deleteArticle(@Param("id") Long id);

    @Update("UPDATE ARTICLES SET title=#{title}, description=#{description}, author_id=#{authorId} WHERE id=#{id}")
    void updateArticle(ArticleEntity article);
}
