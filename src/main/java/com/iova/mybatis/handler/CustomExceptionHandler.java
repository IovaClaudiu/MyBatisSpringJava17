package com.iova.mybatis.handler;

import com.iova.mybatis.exception.BusinessException;
import com.iova.mybatis.exception.RestError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ResponseEntity<RestError> handleBusinessException(final BusinessException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(RestError.builder()
                        .status(exception.getStatus().toString())
                        .reason(exception.getReason())
                        .build());
    }
}
