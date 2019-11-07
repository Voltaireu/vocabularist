package com.voltaireu.vocabularist.website;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScrapperService {

    public ScrapperService() {
    }

    Document getWebsite(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    String parseHtmlToString(Document doc) {
        return doc.body().text();
    }
}
