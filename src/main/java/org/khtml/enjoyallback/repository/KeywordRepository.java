package org.khtml.enjoyallback.repository;


import org.khtml.enjoyallback.entity.Keyword;
import org.khtml.enjoyallback.global.KeywordEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Keyword findByKeyword(KeywordEnum keyword);
}
