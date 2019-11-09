package com.voltaireu.vocabularist.word;

import com.voltaireu.vocabularist.user.User;
import com.voltaireu.vocabularist.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private UserService userService;
    private UserWordRepository userWordRepository;
    private WordRepository wordRepository;

    public WordService(UserService userService, UserWordRepository userWordRepository, WordRepository wordRepository) {
        this.userService = userService;
        this.userWordRepository = userWordRepository;
        this.wordRepository = wordRepository;
    }

    public void addUserWord(long userId, UserWord userWord) {
        User userReference = userService.getUserReference(userId);

        userReference.addUserWord(userWord);
        userService.save(userReference);

        userWord.setUser(userReference);
        userWordRepository.save(userWord);
    }

    public boolean doesWordExist(Word word) {
        String wordText = word.getText();
        return wordRepository.existsByText(wordText);
    }

    public void createWord(Word word) {
    }
}
