package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.dictionary.Dictionary;
import com.voltaireu.vocabularist.user.User;
import com.voltaireu.vocabularist.user.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WebsiteService {

    private final WebsiteRepository websiteRepository;
    private final UserService userService;
    private final EntityManager entityManager;

    public WebsiteService(WebsiteRepository websiteRepository, UserService userService, EntityManager entityManager) {
        this.websiteRepository = websiteRepository;
        this.userService = userService;
        this.entityManager = entityManager;
    }

    public List<Website> getAllUserWebsites(long userId) {
        User userReference = userService.getUser(userId);
        return websiteRepository.findAllByUser(userReference);
    }

    public Dictionary getDictionary(long websiteId) {
        Website website = websiteRepository.findById(websiteId)
                .orElseThrow(() -> new NoSuchElementException(String.format("No website with id %d found!", websiteId)));
        return website.getDictionary();
    }

    public void addDictionary(long websiteId, Dictionary dictionary) {
        Website websiteReference = entityManager.getReference(Website.class, websiteId);
        websiteReference.setDictionary(dictionary);
        websiteRepository.save(websiteReference);
    }

    public void add(long userId, Website website) {
        //If Website with given User and url exists return 409 Conflict
        User userReference = userService.getUserReference(userId);
        website.setUser(userReference);
        websiteRepository.save(website);
    }
}
