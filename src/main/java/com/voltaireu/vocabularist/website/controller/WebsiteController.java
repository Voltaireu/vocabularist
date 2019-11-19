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

    @GetMapping("/users/{userId}/websites")
    public List<Website> getAllUserWebsites(@PathVariable long userId) {
        return websiteService.getAllUserWebsites(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("users/{userId}/websites")
    public Website addWebsite(@PathVariable long userId, @RequestBody Website website) {
        return websiteService.add(userId, website);
    }

    @GetMapping("/websites/{websiteId}/words")
    public List<WebsiteWordDTO> getAllWebsiteWords(@PathVariable long websiteId) {
        return websiteService.getAllUserWebsiteWords(websiteId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/websites/{websiteId}/words")
    public WebsiteWord addWebsiteWord(@PathVariable long websiteId, @RequestBody WordAmountDTO wordAmountDTO) {
        return websiteService.addWebsiteWord(websiteId, wordAmountDTO);
    }

    @GetMapping("websites/{websiteId}")
    public Website getWebsite(@PathVariable long websiteId) {
        return websiteService.getWebsite(websiteId);
    }
}
