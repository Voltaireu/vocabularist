package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteWordRepository extends JpaRepository<WebsiteWord, Integer> {
    boolean existsByWebsiteAndWord(Website website, Word word);

    List<WebsiteWord> findAllByWebsite(Website websiteReference);
}
