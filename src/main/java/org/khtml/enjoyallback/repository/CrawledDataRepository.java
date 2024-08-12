package org.khtml.enjoyallback.repository;

import org.khtml.enjoyallback.entity.CrawledData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawledDataRepository extends JpaRepository<CrawledData, Long> {
    boolean existsByUrl(String url);
}
