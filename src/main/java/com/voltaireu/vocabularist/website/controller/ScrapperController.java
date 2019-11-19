package com.voltaireu.vocabularist.website.controller;

import com.voltaireu.vocabularist.website.WordAmountDTO;
import com.voltaireu.vocabularist.website.service.ScrapperService;
import com.voltaireu.vocabularist.website.service.TextAnalysisService;
import org.hibernate.validator.constraints.URL;
import org.jsoup.nodes.Document;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class ScrapperController {

    private ScrapperService scrapperService;
    private TextAnalysisService textAnalysisService;

    public ScrapperController(ScrapperService scrapperService, TextAnalysisService textAnalysisService) {
        this.scrapperService = scrapperService;
        this.textAnalysisService = textAnalysisService;
    }

    @GetMapping("/fetch-website")
    public List<WordAmountDTO> fetchWebsite(@NotNull @URL @RequestParam String url) {
        Document website = scrapperService.getWebsite(url);
        String websiteBodyString = scrapperService.parseHtmlToString(website);
        return textAnalysisService.countWords(websiteBodyString);
    }
}
