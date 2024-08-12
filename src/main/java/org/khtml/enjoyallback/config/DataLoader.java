package org.khtml.enjoyallback.config;

import org.khtml.enjoyallback.entity.Keyword;
import org.khtml.enjoyallback.global.KeywordEnum;
import org.khtml.enjoyallback.repository.KeywordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadKeywords(KeywordRepository keywordRepository) {
        return args -> {
            for (KeywordEnum keywordEnum : KeywordEnum.values()) {
                if (keywordRepository.findByKeyword(keywordEnum) == null) {
                    Keyword keyword = new Keyword();
                    keyword.setKeyword(keywordEnum);
                    keywordRepository.save(keyword);
                }
            }
        };
    }
}

