package com.voltaireu.vocabularist.word;

import org.springframework.web.bind.annotation.*;

@RestController
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PatchMapping("/words/{wordId}")
    public void updateWordKnown(@PathVariable long wordId, @RequestBody WordKnownDTO wordKnownDTO) {
        wordService.updateWordKnown(wordId, wordKnownDTO);
    }
}
