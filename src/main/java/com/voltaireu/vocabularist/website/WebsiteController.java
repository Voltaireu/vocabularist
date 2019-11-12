package com.voltaireu.vocabularist.website;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/websites/{websiteId}/words")
    public WebsiteWord addWebsiteWord(@PathVariable long websiteId, @RequestBody WordAmountDTO wordAmountDTO) {
        return websiteService.addWebsiteWord(websiteId, wordAmountDTO);
    }
}
