package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.dictionary.Dictionary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebsiteController {

    private WebsiteService websiteService;

    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("users/{userId}/websites")
    public Website addWebsite(@PathVariable long userId, @RequestBody Website website) {
        websiteService.add(userId, website);
        return website;
    }

    @GetMapping("/users/{userId}/websites")
    public List<Website> getAllUserWebsites(@PathVariable long userId) {
        return websiteService.getAllUserWebsites(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/websites/{websiteId}/dictionaries")
    public Dictionary addDictionary(@PathVariable long websiteId, @RequestBody Dictionary dictionary) {
        websiteService.addDictionary(websiteId, dictionary);
        return dictionary;
    }

    @GetMapping("/websites/{websiteId}/dictionaries")
    public Dictionary getWebsiteDictionary(@PathVariable long websiteId) {
        return websiteService.getDictionary(websiteId);
    }

}
