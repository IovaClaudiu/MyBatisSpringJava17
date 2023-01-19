package com.iova.mybatis.security.user;

import com.iova.mybatis.security.user.impl.CustomUserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {

    @Override
    CustomUserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException;

}
