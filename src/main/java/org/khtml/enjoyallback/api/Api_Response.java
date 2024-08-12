package org.khtml.enjoyallback.api;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Api_Response<T> {
    private int code;
    private String message;
    private T result;

    @Builder
    public Api_Response(final int code, final String message, final T result) {
        this.result = result;
        this.code = code;
        this.message = message;
    }
}
