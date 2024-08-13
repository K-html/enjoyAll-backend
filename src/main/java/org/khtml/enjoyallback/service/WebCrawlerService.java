package org.khtml.enjoyallback.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.HttpStatusException;
import org.khtml.enjoyallback.entity.CrawledData;
import org.khtml.enjoyallback.repository.CrawledDataRepository;
import org.khtml.enjoyallback.util.UrlGenerator;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebCrawlerService {

    private static final Logger logger = LoggerFactory.getLogger(WebCrawlerService.class);

    final private CrawledDataRepository crawledDataRepository;

    @PostConstruct
    public void init() {
        List<String> urls = UrlGenerator.generateUrls();
        for (String url : urls) {
            crawl(url);
        }
    }

    private void crawl(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            Element element = document.selectFirst(".cont_box");
            String content = element != null ? element.text() : "";

            Element titleElement = document.selectFirst("div.h3_box > h3.top_banner_bbs");
            String title = titleElement != null ? titleElement.text() : "";

            if (!content.isEmpty() && !title.isEmpty() && !isUrlAlreadyCrawled(url)) {
                CrawledData crawledData = new CrawledData();
                crawledData.setUrl(url);
                crawledData.setContent(content);
                crawledData.setTitle(title);
                logger.info("Crawled data: {}", crawledData);
                crawledDataRepository.save(crawledData);

                logger.info("Successfully crawled: {}", url);
            } else {
                if (content.isEmpty() || title.isEmpty()) {
                    logger.info("Skipped URL due to missing title or content: {}", url);
                } else {
                    logger.info("Skipped URL due to duplication: {}", url);
                }
            }
        } catch (HttpStatusException e) {
            logger.error("HTTP error fetching URL. Status={}, URL={}", e.getStatusCode(), e.getUrl(), e);
        } catch (IOException e) {
            logger.error("Failed to fetch data from: {}", url, e);
        }
    }

    private boolean isUrlAlreadyCrawled(String url) {
        return crawledDataRepository.existsByUrl(url);
    }
}

