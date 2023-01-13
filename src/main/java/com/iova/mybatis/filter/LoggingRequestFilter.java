package com.iova.mybatis.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "LoggingRequestFilter", urlPatterns = "/api/*")
public class LoggingRequestFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggingRequestFilter.class);

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

        LOGGER.info("Request Info: [" + request.getMethod() + ", " + request.getRequestURI() + "]");

        final CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
        final byte[] content = cachedBodyHttpServletRequest.getInputStream().readAllBytes();
        if (content.length > 0)
            LOGGER.info("Payload: " + new String(content, StandardCharsets.UTF_8));

        filterChain.doFilter(cachedBodyHttpServletRequest, response);
    }
}
