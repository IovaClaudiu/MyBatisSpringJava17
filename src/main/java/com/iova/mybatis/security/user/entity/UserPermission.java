package com.iova.mybatis.security.user.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserPermission implements GrantedAuthority {
    ADMIN,
    USER,
    VIEWER;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
