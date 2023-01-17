package com.iova.mybatis.security.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JsonBackReference
    public UserEntity user;

    public UserPermission permission = UserPermission.User;
}
