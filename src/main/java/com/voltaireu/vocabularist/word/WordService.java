package com.voltaireu.vocabularist.word;

import com.voltaireu.vocabularist.other.ResourceNotFoundException;
import com.voltaireu.vocabularist.user.model.User;
import com.voltaireu.vocabularist.user.UserService;
import com.voltaireu.vocabularist.word.model.UserWord;
import com.voltaireu.vocabularist.word.model.Word;
import org.springframework.stereotype.Service;

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

    public Word getWordByText(String text, boolean allowCreate) {
        if (allowCreate) {
            return wordRepository.findByText(text)
                    .orElse(wordRepository.save(new Word(text)));
        }

        return wordRepository.findByText(text)
                .orElseThrow(()-> new ResourceNotFoundException(Word.class, "text", text));
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
