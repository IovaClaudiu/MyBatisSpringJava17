package com.iova.mybatis.security.user.impl;

import com.iova.mybatis.security.user.CustomUserDetailsService;
import com.iova.mybatis.security.user.entity.UserEntity;
import com.iova.mybatis.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsernameAndEnabledIsTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist"));
        return new CustomUserDetailsImpl(user);
    }

}
