package com.iova.mybatis.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import static com.iova.mybatis.security.user.entity.UserPermission.*;

@Configuration
@EnableMethodSecurity
public class MethodSecurityConfiguration {

    /**
     * "
     * We expose MethodSecurityExpressionHandler using a static method to ensure that Spring publishes
     * it before it initializes Spring Security’s method security @Configuration classes.
     * <p>
     * LINK: <a href="https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html">...</a>
     * "
     */
    @Bean
    public static MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy());
        return handler;
    }

    @Bean
    public static RoleHierarchy roleHierarchy() {
        String newLine = System.getProperty("line.separator");
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy(
                String.join(newLine,
                        ADMIN.getAuthority() + " > " + USER.getAuthority(),
                        USER.getAuthority() + " > " + VIEWER.getAuthority()).trim()
        );
        return hierarchy;
    }
}
