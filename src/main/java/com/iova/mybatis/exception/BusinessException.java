package com.iova.mybatis.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String reason;

    public BusinessException(final HttpStatus status, final String reason) {
        super();
        this.status = status;
        this.reason = reason;
    }
}
