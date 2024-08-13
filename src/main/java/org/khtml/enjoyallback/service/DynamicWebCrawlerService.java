package org.khtml.enjoyallback.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.khtml.enjoyallback.repository.CrawledDataRepository;
import org.khtml.enjoyallback.util.ApiResponseUtil;
import org.khtml.enjoyallback.util.UrlGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DynamicWebCrawlerService {
    private static final Logger logger = LoggerFactory.getLogger(WebCrawlerService.class);
    final private CrawledDataRepository crawledDataRepository;

    @PostConstruct
    public void init() throws Exception {
        String url = "https://www.bokjiro.go.kr/ssis-tbu/twataa/wlfareInfo/moveTWAT52011M" +
                ".do?wlfareInfoId=WLF00001061&wlfareInfoReldBztpCd=01";
//        scrapeDynamicPage(url);
    }

    public void scrapeDynamicPage(String url) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
//        WebDriver driver = new RemoteWebDriver(new URL("http://selenium-chrome:4444/wd/hub"), options);

        try {
            driver.get(url);
            Thread.sleep(1000); // 페이지 로딩 대기

            String pageSource = driver.getPageSource();
            System.out.println(pageSource); // 페이지 소스를 콘솔에 출력
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
