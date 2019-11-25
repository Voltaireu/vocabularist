package com.voltaireu.vocabularist.website.controller;

import com.voltaireu.vocabularist.website.WebsiteWordDTO;
import com.voltaireu.vocabularist.website.WordAmountDTO;
import com.voltaireu.vocabularist.website.model.Website;
import com.voltaireu.vocabularist.website.model.WebsiteWord;
import com.voltaireu.vocabularist.website.service.WebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebsiteController {

    private final WebsiteService websiteService;

    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("/users/me/websites")
    public List<Website> getAllUserWebsites() {
        return websiteService.getAllUserWebsites();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/websites")
    public Website addWebsite(@RequestBody Website website) {
        return websiteService.add(website);
    }

    @GetMapping("/websites/{websiteId}/words")
    public List<WebsiteWordDTO> getAllWebsiteWords(@PathVariable long websiteId) {
        return websiteService.getAllUserWebsiteWords(websiteId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/websites/{websiteId}/words")
    public WebsiteWordDTO addWebsiteWord(@PathVariable long websiteId, @RequestBody WordAmountDTO wordAmountDTO) {
        return websiteService.addWebsiteWord(websiteId, wordAmountDTO);
    }

    @GetMapping("/websites/{websiteId}")
    public Website getWebsite(@PathVariable long websiteId) {
        return websiteService.getWebsite(websiteId);
    }
}
