package com.voltaireu.vocabularist.word;

import com.voltaireu.vocabularist.user.User;
import com.voltaireu.vocabularist.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private final UserService userService;
    private final UserWordRepository userWordRepository;

    public WordService(UserService userService, UserWordRepository userWordRepository) {
        this.userService = userService;
        this.userWordRepository = userWordRepository;
    }

    //TODO Refactor - Lambda Expressions?
    public void increaseUserWordAmount(Word word, int amount) {
        UserWord userWord = getUserWord(word);
        int previousAmount = userWord.getAmountInGeneral();
        userWord.setAmountInGeneral(previousAmount + amount);
        userWordRepository.save(userWord);
   }

    public void decreaseUserWordAmount(Word word, int amount) {
        UserWord userWord = getUserWord(word);
        int previousAmount = userWord.getAmountInGeneral();
        int difference = previousAmount - amount;
        if (difference > 0) {
            userWord.setAmountInGeneral(difference);
        } else {
            userWordRepository.delete(userWord);
        }
        userWordRepository.save(userWord);
    }

    public UserWord getUserWord(Word word) {
        User user = userService.getAuthenticatedUser();
        UserWord userWord = userWordRepository.findByWordAndUser(word, user)
                .orElse(new UserWord());
        userWord.setWord(word);
        userWord.setUser(user);
        return userWord;
    }
}
