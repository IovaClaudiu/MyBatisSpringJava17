package com.iova.mybatis.security.user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public LocalDateTime dateCreated = LocalDateTime.now();

    public LocalDateTime lastModifiedDate;

    @Column(unique = true)
    public String username;

    public String firstName;

    public String lastName;

    public String description;

    public Boolean enabled;

    public String password;

    @Column(unique = true)
    public String legacyApiKey;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    public Set<RoleEntity> roles;
}
