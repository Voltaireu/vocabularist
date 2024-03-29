package com.voltaireu.vocabularist.website.service;

import com.voltaireu.vocabularist.other.NoContentException;
import com.voltaireu.vocabularist.other.ResourceAlreadyExistsException;
import com.voltaireu.vocabularist.other.ResourceNotFoundException;
import com.voltaireu.vocabularist.security.acl.AclPermission;
import com.voltaireu.vocabularist.user.model.User;
import com.voltaireu.vocabularist.user.UserService;
import com.voltaireu.vocabularist.website.WebsiteWordDTO;
import com.voltaireu.vocabularist.website.WordAmountDTO;
import com.voltaireu.vocabularist.website.model.Website;
import com.voltaireu.vocabularist.website.model.WebsiteWord;
import com.voltaireu.vocabularist.website.repository.WebsiteRepository;
import com.voltaireu.vocabularist.website.repository.WebsiteWordRepository;
import com.voltaireu.vocabularist.word.model.Word;
import com.voltaireu.vocabularist.word.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class WebsiteService {

    private final WordService wordService;
    private final UserService userService;
    private final WebsiteRepository websiteRepository;
    private final WebsiteWordRepository websiteWordRepository;
    private final ModelMapper modelMapper;

    public WebsiteService(WebsiteRepository websiteRepository, UserService userService, WebsiteWordRepository websiteWordRepository, WordService wordService, ModelMapper modelMapper) {
        this.websiteRepository = websiteRepository;
        this.userService = userService;
        this.websiteWordRepository = websiteWordRepository;
        this.wordService = wordService;
        this.modelMapper = modelMapper;
    }

    public List<Website> getAllUserWebsites() {
        User user = userService.getAuthenticatedUser();
        return websiteRepository.findAllByUser(user);
    }

    @Transactional
    public Website add(Website website) {
        User user = userService.getAuthenticatedUser();

        String url = website.getUrl();
        if(websiteRepository.existsByUserAndUrl(user, url)) {
            String message = "Website with User with id " + user.getId() + " and url " + url + " already exists!";
            throw new ResourceAlreadyExistsException(message);
        };

        website.setUser(user);
        Website result = websiteRepository.save(website);
        userService.addPrincipalPermission(Website.class, website.getId(), AclPermission.ADMINISTRATION);
        return result;
    }

    public WebsiteWordDTO addWebsiteWord(long websiteId, WordAmountDTO wordAmountDTO) {
        if(!websiteRepository.existsById(websiteId)) {
            throw new ResourceNotFoundException(Website.class, websiteId);
        }

        String text = wordAmountDTO.getText();
        int amount = wordAmountDTO.getAmount();
        Word word = wordService.getWordByText(text, true);

        Website websiteReference = websiteRepository.getOne(websiteId);
        if(websiteWordRepository.existsByWebsiteAndWord(websiteReference, word)) {
            String message = "WebsiteWord with text " + text + " is already in Website with id " + websiteId + "!";
            throw new ResourceAlreadyExistsException(message);
        }

        WebsiteWord websiteWord = new WebsiteWord(amount, word);
        websiteWord.setWebsite(websiteReference);
        wordService.increaseUserWordAmount(word, amount);
        websiteWord = websiteWordRepository.save(websiteWord);
        return modelMapper.map(websiteWord, WebsiteWordDTO.class);
    }

    public List<WebsiteWordDTO> getAllUserWebsiteWords(long websiteId) {
        if(!websiteRepository.existsById(websiteId)) {
            throw new ResourceNotFoundException(Website.class, websiteId);
        }

        List<WebsiteWord> websiteWords = websiteWordRepository.findAllByWebsiteId(websiteId);
        if(websiteWords.isEmpty()) {
            throw new NoContentException();
        }

        List<WebsiteWordDTO> websiteWordDTOs = new LinkedList<>();
        websiteWords.forEach((websiteWord)-> websiteWordDTOs.add(modelMapper.map(websiteWord, WebsiteWordDTO.class)));
        return websiteWordDTOs;
    }

    @PostAuthorize("hasPermission(returnObject, 'READ') or hasPermission(returnObject, 'ADMINISTRATION')")
    public Website getWebsite(long websiteId) {
        return websiteRepository.findById(websiteId)
                .orElseThrow(() -> new ResourceNotFoundException(Website.class, websiteId));
    }
}
