package com.voltaireu.vocabularist.exam;

import com.voltaireu.vocabularist.exam.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findAllByExamId(long examId);
}
