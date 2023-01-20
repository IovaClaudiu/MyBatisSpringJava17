package com.iova.mybatis.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticleDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @OneToOne
    @JoinColumn(name = "id")
    @NotNull
    private AuthorDto author;

}
