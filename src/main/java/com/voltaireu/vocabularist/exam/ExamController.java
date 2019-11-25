package com.voltaireu.vocabularist.exam;

import com.voltaireu.vocabularist.exam.model.Exam;
import com.voltaireu.vocabularist.exam.model.Flashcard;
import com.voltaireu.vocabularist.word.model.Word;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/exams")
    public Exam createExam(@RequestParam long websiteId) {
        return examService.create(websiteId);
    }

    @GetMapping("/exams/{examId}")
    public Exam getExam(@PathVariable long examId) {
        return examService.get(examId);
    }

    @GetMapping("/exams/{examId}/exercise")
    public Word exercise(@PathVariable long examId) {
        return examService.drawRandomWord(examId);
    }

    @PostMapping("/exams/{examId}/flashcards")
    public Flashcard addFlashcard(@PathVariable long examId, @RequestBody FlashcardDTO flashcard) {
        return examService.addFlashcard(examId, flashcard);
    }

    @GetMapping("/exams/{examId}/flashcards")
    public List<Flashcard> getExamFlashcards(@PathVariable long examId) {
        return examService.getFlashcards(examId);
    }

    @PostMapping("/exams/{examId}/finish")
    public void finishExam(@PathVariable long examId) {
        examService.finishExam(examId);
    }
}
