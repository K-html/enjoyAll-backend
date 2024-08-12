package org.khtml.enjoyallback.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.khtml.enjoyallback.api.Api_Response;
import org.khtml.enjoyallback.global.code.CommonErrorCode;
import org.khtml.enjoyallback.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Api_Response<Object>> handle(IllegalArgumentException e) {
        return ApiResponseUtil.createErrorResponse(CommonErrorCode.BAD_REQUEST);
    }

    // 403 권한 오류
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Api_Response<Object>> handle(NullPointerException e) {
        return ApiResponseUtil.createErrorResponse(CommonErrorCode.FORBIDDEN_ERROR);
    }

    // 404 데이터 없음
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Api_Response<Object>> handle(EntityNotFoundException e) {
        return ApiResponseUtil.createErrorResponse(CommonErrorCode.NOT_FOUND);
    }

    // 500 서버 오류
    @ExceptionHandler(InternalError.class)
    protected ResponseEntity<Api_Response<Object>> handle(RuntimeException e) {
        return ApiResponseUtil.createErrorResponse(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }
}
