package com.voltaireu.vocabularist.exam;

import com.voltaireu.vocabularist.exam.model.Exam;
import com.voltaireu.vocabularist.exam.model.Flashcard;
import com.voltaireu.vocabularist.other.NoContentException;
import com.voltaireu.vocabularist.other.ResourceNotFoundException;
import com.voltaireu.vocabularist.security.acl.AclPermission;
import com.voltaireu.vocabularist.security.acl.PermissionManager;
import com.voltaireu.vocabularist.user.UserService;
import com.voltaireu.vocabularist.website.model.Website;
import com.voltaireu.vocabularist.website.model.WebsiteWord;
import com.voltaireu.vocabularist.website.repository.WebsiteRepository;
import com.voltaireu.vocabularist.website.repository.WebsiteWordRepository;
import com.voltaireu.vocabularist.word.WordRepository;
import com.voltaireu.vocabularist.word.model.Word;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService {

    private final WebsiteRepository websiteRepository;
    private final ExamRepository examRepository;
    private final UserService userService;
    private final WebsiteWordRepository websiteWordRepository;
    private final DrawingUtil drawingUtil;
    private final WordRepository wordRepository;
    private final PermissionManager permissionManager;
    private final FlashcardRepository flashcardRepository;

    public ExamService(WebsiteRepository websiteRepository, ExamRepository examRepository, UserService userService, WebsiteWordRepository websiteWordRepository, DrawingUtil drawingUtil, WordRepository wordRepository, PermissionManager permissionManager, FlashcardRepository flashcardRepository) {
        this.websiteRepository = websiteRepository;
        this.examRepository = examRepository;
        this.userService = userService;
        this.websiteWordRepository = websiteWordRepository;
        this.drawingUtil = drawingUtil;
        this.wordRepository = wordRepository;
        this.permissionManager = permissionManager;
        this.flashcardRepository = flashcardRepository;
    }

    @Transactional
    public Exam create(long websiteId) {
        Website website = websiteRepository.findById(websiteId)
                .orElseThrow(() -> new ResourceNotFoundException(Website.class, websiteId));
        Exam exam = new Exam();
        exam.setWebsite(website);
        examRepository.save(exam);

        userService.addPrincipalPermission(Exam.class, exam.getId(), AclPermission.ADMINISTRATION);
        return exam;
    }

    public Word drawRandomWord(long examId) {
        Exam exam = get(examId);
        List<WebsiteWord> websiteWords = websiteWordRepository.findAllByWebsiteId(exam.getWebsite().getId());
        if (websiteWords.isEmpty()) {
            throw new NoContentException();
        }
        return drawingUtil.drawWord(websiteWords);
    }

    @PostAuthorize("hasPermission(returnObject, 'READ') or hasPermission(returnObject, 'ADMINISTRATION')")
    public Exam get(long examId) {
        return examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException(Exam.class, examId));
    }

    @Transactional
    public Flashcard addFlashcard(long examId, FlashcardDTO flashcardDTO) {
        Exam exam = get(examId);
        long wordId = flashcardDTO.getWordId();
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new ResourceNotFoundException(Word.class, wordId));

        Flashcard flashcard = new Flashcard();
        flashcard.setKnown(flashcardDTO.isKnown());
        flashcard.setWord(word);
        flashcard.setExam(exam);
        flashcard = flashcardRepository.save(flashcard);

        permissionManager.setParentAcl(Flashcard.class, flashcard.getId(), Exam.class, examId);

        return flashcard;
    }

    public List<Flashcard> getFlashcards(long examId) {
        List<Flashcard> flashcards = flashcardRepository.findAllByExamId(examId);
        if (flashcards.isEmpty()) {
            throw new NoContentException();
        }
        return flashcards;
    }


    public void finishExam(long examId) {
        Exam exam = get(examId);
        exam.setFinished(true);
        examRepository.save(exam);
    }
}
