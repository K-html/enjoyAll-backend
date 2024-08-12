package org.khtml.enjoyallback.util;

import org.khtml.enjoyallback.api.Api_Response;
import org.khtml.enjoyallback.global.code.CommonErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {

    public static <T> ResponseEntity<Api_Response<T>> createResponse(int code, String message, T result) {
        Api_Response<T> response = Api_Response.<T>builder()
                .code(code)
                .message(message)
                .result(result)
                .build();
        return ResponseEntity.status(code).body(response);
    }
    public static <T> ResponseEntity<Api_Response<T>> createSuccessResponse(String message) {
        return createResponse(HttpStatus.OK.value(), message, null);
    }

    public static <T> ResponseEntity<Api_Response<T>> createSuccessResponse(String message, T result) {
        return createResponse(HttpStatus.OK.value(), message, result);
    }

    public static <T> ResponseEntity<Api_Response<T>> createErrorResponse(CommonErrorCode errorCode) {
        return createResponse(errorCode.getHttpStatus().value(), errorCode.getMessage(), null);
    }
}
