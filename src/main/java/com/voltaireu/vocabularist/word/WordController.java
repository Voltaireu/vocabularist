package com.voltaireu.vocabularist.word;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class WordController {

    private WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{userId}/user-words")
    public UserWord addUserWord(@PathVariable long userId, @RequestBody UserWord userWord) {
        wordService.addUserWord(userId, userWord);
        return userWord;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/words")
    public Word createWord(@RequestBody Word word){
        wordService.createWord(word);
        return word;
    }
}
