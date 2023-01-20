package com.iova.mybatis.entity;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AuthorEntity {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String sex;
}
