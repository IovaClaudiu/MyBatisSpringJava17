package com.iova.mybatis.exception;

import lombok.Builder;

@Builder
public record RestError(String status, String reason) {
}
