package com.voltaireu.vocabularist.dictionary;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DictionaryService {

    private DictionaryRepository dictionaryRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public Dictionary getDictionary(long dictionaryId) {
        return dictionaryRepository.findById(dictionaryId)
                .orElseThrow(() -> new NoSuchElementException(String.format("No Dictionary with id %d found!", dictionaryId)));
    }

    public void addDictionaryWord(long dictionaryId, DictionaryWord dictionaryWord) {
        Dictionary dictionary = dictionaryRepository.getOne(dictionaryId);
        dictionary.addDictionaryWord(dictionaryWord);
    }
}
