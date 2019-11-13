package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.other.ResourceAlreadyExistsException;
import com.voltaireu.vocabularist.other.ResourceNotFoundException;
import com.voltaireu.vocabularist.user.User;
import com.voltaireu.vocabularist.user.UserService;
import com.voltaireu.vocabularist.word.Word;
import com.voltaireu.vocabularist.word.WordRepository;
import com.voltaireu.vocabularist.word.WordService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebsiteService {

    private final WordService wordService;
    private final UserService userService;
    private final WebsiteRepository websiteRepository;
    private final WordRepository wordRepository;
    private final WebsiteWordRepository websiteWordRepository;
    private final ModelMapper modelMapper;

    public WebsiteService(WebsiteRepository websiteRepository, UserService userService, WordRepository wordRepository, WebsiteWordRepository websiteWordRepository, WordService wordService, ModelMapper modelMapper) {
        this.websiteRepository = websiteRepository;
        this.userService = userService;
        this.wordRepository = wordRepository;
        this.websiteWordRepository = websiteWordRepository;
        this.wordService = wordService;
        this.modelMapper = modelMapper;
    }

    public List<Website> getAllUserWebsites(long userId) {
        User user = userService.getUser(userId);
        return websiteRepository.findAllByUser(user);
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

    public List<WebsiteWordDTO> getAllUserWebsiteWords(long websiteId) {
        if(!websiteRepository.existsById(websiteId)) {
            throw new ResourceNotFoundException(Website.class, websiteId);
        }

        Website websiteReference = websiteRepository.getOne(websiteId);
        List<WebsiteWord> websiteWords = websiteWordRepository.findAllByWebsite(websiteReference);

        List<WebsiteWordDTO> websiteWordDTOs = new LinkedList<>();
        websiteWords.forEach((websiteWord)-> websiteWordDTOs.add(modelMapper.map(websiteWord, WebsiteWordDTO.class)));
        return websiteWordDTOs;
    }
}
