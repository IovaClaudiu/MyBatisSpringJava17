package com.iova.mybatis.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDto {

    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String author;


}
