package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteWordRepository extends JpaRepository<WebsiteWord, Integer> {
    boolean existsByWebsiteAndWord(Website website, Word word);
}
