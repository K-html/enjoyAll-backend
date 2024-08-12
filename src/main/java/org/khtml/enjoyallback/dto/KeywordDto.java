package org.khtml.enjoyallback.dto;

import lombok.Data;
import org.khtml.enjoyallback.entity.Keyword;
import org.khtml.enjoyallback.global.KeywordEnum;

@Data
public class KeywordDto {
    private KeywordEnum keyword;

    public static KeywordDto fromEntity(Keyword keyword) {
        KeywordDto keyWordDto = new KeywordDto();
        keyWordDto.setKeyword(keyword.getKeyword()); // keyword 값을 설정하도록 수정
        return keyWordDto;
    }
}
