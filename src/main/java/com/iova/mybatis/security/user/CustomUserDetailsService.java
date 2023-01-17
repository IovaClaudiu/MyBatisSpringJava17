package com.iova.mybatis.security.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {

    @Override
    CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
