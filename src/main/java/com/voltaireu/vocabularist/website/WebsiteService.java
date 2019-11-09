package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.dictionary.Dictionary;
import com.voltaireu.vocabularist.user.User;
import com.voltaireu.vocabularist.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WebsiteService {

    private WebsiteRepository websiteRepository;
    private UserService userService;

    public WebsiteService(WebsiteRepository websiteRepository, UserService userService) {
        this.websiteRepository = websiteRepository;
        this.userService = userService;
    }

    public List<Website> getAllUserWebsites(long userId) {
        User userReference = userService.getUserReference(userId);
        return websiteRepository.findAllByUser(userReference);
    }

    public Dictionary getDictionary(long websiteId) {
        Website website = websiteRepository.findById(websiteId)
                .orElseThrow(() -> new NoSuchElementException(String.format("No website with id %d found!", websiteId)));
        return website.getDictionary();
    }

    public void addDictionary(long websiteId, Dictionary dictionary) {
        Website websiteReference = websiteRepository.getById(websiteId);
        websiteReference.setDictionary(dictionary);
        dictionary.setWebsite(websiteReference);
    }

    public void add(long userId, Website website) {
        User userReference = userService.getUserReference(userId);
        userReference.addWebsite(website);
        website.setUser(userReference);
    }
}
