package com.voltaireu.vocabularist.dictionary;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dictionaries")
public class DictionaryController {

    private DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/{dictionaryId}")
    public Dictionary getDictionary(@PathVariable long dictionaryId){
        return dictionaryService.getDictionary(dictionaryId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{dictionaryId}/dictionary-words")
    public DictionaryWord addDictionaryWord(@PathVariable long dictionaryId, @RequestBody DictionaryWord dictionaryWord) {
        dictionaryService.addDictionaryWord(dictionaryId, dictionaryWord);
        return dictionaryWord;
    }
}
