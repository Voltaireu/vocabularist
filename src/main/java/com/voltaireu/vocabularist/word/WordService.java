package com.voltaireu.vocabularist.word;

import com.voltaireu.vocabularist.user.User;
import com.voltaireu.vocabularist.user.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WordService {

    private final UserService userService;
    private final UserWordRepository userWordRepository;
    private final WordRepository wordRepository;

    public WordService(UserService userService, UserWordRepository userWordRepository, WordRepository wordRepository) {
        this.userService = userService;
        this.userWordRepository = userWordRepository;
        this.wordRepository = wordRepository;
    }

    public UserWord createUserWord() {
        UserWord userWord = new UserWord();
        userWordRepository.save(userWord);
        return userWord;
    }

    public void createWord(Word word) {
        //If Word with given text exists return 409 Conflict
        wordRepository.save(word);
    }

    public Word getWordByText(String text) {
        return wordRepository.findByText(text)
                .orElseThrow(() -> new NoSuchElementException(String.format("No word with text %s found!", text)));
    }

    public void addUserWord(long userId, long userWordId) {
        User userReference = userService.getUserReference(userId);
        UserWord userWord = userWordRepository.getOne(userWordId);
        userWord.setUser(userReference);
        userWordRepository.save(userWord);
    }

    public void addWord(long userWordId, long wordId) {
        UserWord userWord = userWordRepository.getOne(userWordId);
        Word wordReference = wordRepository.getOne(wordId);
        userWord.setWord(wordReference);
        userWordRepository.save(userWord);
    }
}
