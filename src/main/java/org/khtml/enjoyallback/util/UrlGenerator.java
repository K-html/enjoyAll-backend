package org.khtml.enjoyallback.util;

import java.util.ArrayList;
import java.util.List;

public class UrlGenerator {

    private static final String BASE_URL = "https://www.yongin.go.kr/home/www/www01";

    public static List<String> generateUrls() {
        List<String> urls = new ArrayList<>();

        addRangeUrls(urls, "www01_01", "02", 1, 6);
        addRangeUrls(urls, "www01_01", "03", 1, 4);
        addRangeUrls(urls, "www01_01", "04", 1, 3);
        addSingleUrl(urls, "www01_01", "05", 1);

        addRangeUrls(urls, "www01_02", "01", 1, 9);
        addRangeUrls(urls, "www01_02", "02", 1, 5);

        addSingleUrlWithoutPage(urls, "www01_03", "01");
        addRangeUrls(urls, "www01_03", "02", 1, 3);
        addSingleUrlWithoutPage(urls, "www01_03", "03");
        addSingleUrlWithoutPage(urls, "www01_03", "09");

        addRangeUrls(urls, "www01_04", "01", 1, 6);
        addRangeUrls(urls, "www01_04", "02", 1, 9);
        addMultipleSingleUrls(urls, "www01_04", new String[]{"03", "04", "05", "06", "07"});

        addRangeUrls(urls, "www01_05", "05", 2, 14);
        addRangeUrls(urls, "www01_05", "06", 1, 4);

        addRangeUrls(urls, "www01_10", "01", 1, 3);
        addRangeUrls(urls, "www01_10", "02", 1, 3);
        addRangeUrls(urls, "www01_10", "03", 1, 4);

        addRangeUrls(urls, "www01_09", "02", 1, 11);

        return urls;
    }

    private static void addRangeUrls(List<String> urls, String mainSection, String subSection, int start, int end) {
        for (int i = start; i <= end; i++) {
            urls.add(formatUrl(mainSection, subSection, i));
        }
    }

    private static void addSingleUrl(List<String> urls, String mainSection, String subSection, int page) {
        urls.add(formatUrl(mainSection, subSection, page));
    }

    private static void addSingleUrlWithoutPage(List<String> urls, String mainSection, String subSection) {
        urls.add(formatUrlWithoutPage(mainSection, subSection));
    }

    private static void addMultipleSingleUrls(List<String> urls, String mainSection, String[] subSections) {
        for (String subSection : subSections) {
            addSingleUrlWithoutPage(urls, mainSection, subSection);
        }
    }

    private static String formatUrl(String mainSection, String subSection, int page) {
        String pageStr = String.format("%02d", page);
        return String.format("%s/%s/%s_%s/%s_%s_%s.jsp", BASE_URL, mainSection, mainSection, subSection, mainSection, subSection, pageStr);
    }

    private static String formatUrlWithoutPage(String mainSection, String subSection) {
        return String.format("%s/%s/%s_%s.jsp", BASE_URL, mainSection, mainSection, subSection);
    }

}

