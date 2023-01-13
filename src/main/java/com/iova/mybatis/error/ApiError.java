package com.iova.mybatis.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record ApiError(HttpStatus status, String message, List<String> errors) {
}
