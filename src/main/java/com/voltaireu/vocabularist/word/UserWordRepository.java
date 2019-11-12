package com.voltaireu.vocabularist.word;

import com.voltaireu.vocabularist.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
    Optional<UserWord> findByWordAndUser(Word word, User user);
}
