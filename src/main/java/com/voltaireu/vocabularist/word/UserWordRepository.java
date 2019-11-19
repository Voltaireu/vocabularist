package com.voltaireu.vocabularist.word;

import com.voltaireu.vocabularist.user.model.User;
import com.voltaireu.vocabularist.word.model.UserWord;
import com.voltaireu.vocabularist.word.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
    Optional<UserWord> findByWordAndUser(Word word, User user);
}
