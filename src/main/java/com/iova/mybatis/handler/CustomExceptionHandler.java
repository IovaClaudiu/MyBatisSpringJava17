package com.iova.mybatis.handler;

import com.iova.mybatis.error.ApiError;
import com.iova.mybatis.error.RestError;
import com.iova.mybatis.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ResponseEntity<RestError> handleBusinessException(BusinessException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(RestError.builder()
                        .status(exception.getStatus().toString())
                        .reason(exception.getReason())
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, final WebRequest request) {
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiError apiErrors = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .errors(errors)
                .build();

        return handleExceptionInternal(ex, apiErrors, headers, apiErrors.status(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        log.error("Error occurred: ", ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong, please contact and administrator!");
    }
}
