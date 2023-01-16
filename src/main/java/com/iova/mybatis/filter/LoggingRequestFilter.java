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
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "LoggingRequestFilter", urlPatterns = "/api/*")
public class LoggingRequestFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggingRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        logRequest(requestWrapper);

        logResponse(requestWrapper, responseWrapper);

        responseWrapper.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper requestWrapper) {
        LOGGER.info("Request Info: [ {} , {} ]", requestWrapper.getMethod(), requestWrapper.getRequestURI());

        final byte[] content = requestWrapper.getContentAsByteArray();
        if (content.length > 0)
            LOGGER.info("Payload: {}", new String(content, StandardCharsets.UTF_8));
    }

    private void logResponse(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) {
        LOGGER.info("Response Info: [ method= {}, status={} ]", requestWrapper.getMethod(), responseWrapper.getStatus());
        
        final byte[] responseContent = responseWrapper.getContentAsByteArray();
        if (responseContent.length > 0)
            LOGGER.info("Response body: [ {} ]", new String(responseContent, StandardCharsets.UTF_8));
    }

}
