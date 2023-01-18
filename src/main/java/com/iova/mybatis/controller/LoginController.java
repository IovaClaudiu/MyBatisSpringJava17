package com.iova.mybatis.controller;

import com.iova.mybatis.dto.LoginResponseDto;
import com.iova.mybatis.dto.UserDto;
import com.iova.mybatis.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.iova.mybatis.config.Routes.LOGIN_ROUTE;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(path = LOGIN_ROUTE)
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserDto user) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            String token = jwtTokenProvider.createToken(user.getUsername(), authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toUnmodifiableSet()));

            return ResponseEntity.ok(LoginResponseDto.builder()
                    .username(user.getUsername())
                    .token(token)
                    .build());
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Invalid Credentials", exception);
        }
    }
}
