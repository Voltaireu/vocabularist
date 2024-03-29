package com.voltaireu.vocabularist.word;

import com.voltaireu.vocabularist.word.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findByText(String text);
}
