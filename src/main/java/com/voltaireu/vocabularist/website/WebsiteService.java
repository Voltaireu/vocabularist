package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.user.User;
import com.voltaireu.vocabularist.user.UserService;
import com.voltaireu.vocabularist.word.Word;
import com.voltaireu.vocabularist.word.WordRepository;
import com.voltaireu.vocabularist.word.WordService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        //If Website with given User and url exists return 409 Conflict
        User userReference = userService.getUserReference(userId);
        website.setUser(userReference);
        return websiteRepository.save(website);
    }

    public WebsiteWord addWebsiteWord(long websiteId, WordAmountDTO wordAmountDTO) {
        String text = wordAmountDTO.getText();
        int amount = wordAmountDTO.getAmount();

        Word word = wordRepository.findByText(text)
                .orElse(wordRepository.save(new Word(text)));
        WebsiteWord websiteWord = new WebsiteWord(amount, word);

        Website websiteReference = websiteRepository.getOne(websiteId);
        websiteWord.setWebsite(websiteReference);

        websiteWordRepository.save(websiteWord);

        wordService.increaseUserWordAmount(word, amount);

        return websiteWord;
    }
}
