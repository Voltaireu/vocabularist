package com.voltaireu.vocabularist.website;

import org.hibernate.validator.constraints.URL;
import org.jsoup.nodes.Document;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
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

    @PostMapping("/fetch-website")
    public List<WordAmountDTO> fetchWebsite(@NotNull @URL @RequestParam String url) throws IOException {
        Document website = scrapperService.getWebsite(url);
        String websiteBodyString = scrapperService.parseHtmlToString(website);
        return textAnalysisService.countWords(websiteBodyString);
    }
}
