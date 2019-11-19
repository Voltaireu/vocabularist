package com.voltaireu.vocabularist.website.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScrapperService {

    public ScrapperService() {
    }

    public Document getWebsite(String url) {
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Server can not connect to website with url " + url + " !");
        }
        return document;
    }

    public String parseHtmlToString(Document doc) {
        return doc.body().text();
    }
}
