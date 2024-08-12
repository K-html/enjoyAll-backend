package org.khtml.enjoyallback.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    /**
     * ******************************* Global Error CodeList ***************************************
     * HTTP Status Code
     * 400 : Bad Request
     * 401 : Unauthorized
     * 403 : FORBIDDEN
     * 404 : Not Found
     * 500 : Internal Server Error
     * *********************************************************************************************
     */

    // [400] BAD_REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
    MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "Missing Servlet RequestParameter"),
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid RequestParameter"),
    MISSING_REQUEST_BODY(HttpStatus.BAD_REQUEST, "Required request body is missing"),

    // [401] UNAUTHORIZED,
    MISSING_SOCIAL_INFO(HttpStatus.UNAUTHORIZED, "User social info is missing(ex: #socialId, #socialEmail)"),
    MISSING_TOKEN(HttpStatus.UNAUTHORIZED, "Token is missing."),
    INVALID_JWT_ERROR(HttpStatus.UNAUTHORIZED, "The provided JWT token is invalid"),
    EXPIRED_JWT_ERROR(HttpStatus.UNAUTHORIZED,  "The provided JWT token is expired"),

    // [403] FORBIDDEN
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN, "No data access"),

    // [404] NOT_FOUND
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not Found"),

    // [500] INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}