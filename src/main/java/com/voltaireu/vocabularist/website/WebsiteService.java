package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.other.ResourceAlreadyExistsException;
import com.voltaireu.vocabularist.user.User;
import com.voltaireu.vocabularist.user.UserService;
import com.voltaireu.vocabularist.word.Word;
import com.voltaireu.vocabularist.word.WordRepository;
import com.voltaireu.vocabularist.word.WordService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class WebsiteService {

    private final WebsiteRepository websiteRepository;
    private final UserService userService;
    private final WordRepository wordRepository;
    private final WebsiteWordRepository websiteWordRepository;
    private final WordService wordService;

    public WebsiteService(WebsiteRepository websiteRepository, UserService userService, WordRepository wordRepository, WebsiteWordRepository websiteWordRepository, WordService wordService) {
        this.websiteRepository = websiteRepository;
        this.userService = userService;
        this.wordRepository = wordRepository;
        this.websiteWordRepository = websiteWordRepository;
        this.wordService = wordService;
    }

    public List<Website> getAllUserWebsites(long userId) {
        User userReference = userService.getUser(userId);
        return websiteRepository.findAllByUser(userReference);
    }

    public Website add(long userId, Website website) {
        User userReference = userService.getUserReference(userId);

        String url = website.getUrl();
        if(websiteRepository.existsByUserAndUrl(userReference, url)) {
            String message = "Website with User Id " + userId + " and url " + url + " already exists!";
            throw new ResourceAlreadyExistsException(message);
        };

        website.setUser(userReference);
        return websiteRepository.save(website);
    }

    public WebsiteWord addWebsiteWord(long websiteId, WordAmountDTO wordAmountDTO) {
        String text = wordAmountDTO.getText();
        int amount = wordAmountDTO.getAmount();

        Word word = wordRepository.findByText(text)
                .orElse(wordRepository.save(new Word(text)));

        Website websiteReference = websiteRepository.getOne(websiteId);
        if(websiteWordRepository.existsByWebsiteAndWord(websiteReference, word)) {
            String message = "WebsiteWord with text " + text + " is already in Website with id " + websiteId + "!";
            throw new ResourceAlreadyExistsException(message);
        }

        WebsiteWord websiteWord = new WebsiteWord(amount, word);
        websiteWord.setWebsite(websiteReference);
        websiteWordRepository.save(websiteWord);

        wordService.increaseUserWordAmount(word, amount);

        return websiteWord;
    }
}
