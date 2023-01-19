package com.iova.mybatis.error;

import lombok.Builder;

@Builder
public record RestError(String status, String reason) {
}
