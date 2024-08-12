package org.khtml.enjoyallback.dto;

import lombok.Getter;
import org.khtml.enjoyallback.global.KeywordEnum;

@Getter
public class UserReqDto {
    private Long userId;
    private String socialName;
    private KeywordEnum keyword;
}
