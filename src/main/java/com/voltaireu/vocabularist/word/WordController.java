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
    public void addUserWord(@PathVariable long userId, @RequestParam(name = "id") long userWordId) {
        wordService.addUserWord(userId, userWordId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user-words/{userWordId}/words")
    public void addWord(@PathVariable long userWordId, @RequestParam(name = "id") long wordId) {
        wordService.addWord(userWordId, wordId);
    }

    @PostMapping("/user-words")
    public UserWord createUserWord() {
        return wordService.createUserWord();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/words")
    public Word createWord(@RequestBody Word word){
        wordService.createWord(word);
        return word;
    }

    @GetMapping("/words")
    public Word getWord(@RequestParam String text) {
        return wordService.getWordByText(text);
    }
}
