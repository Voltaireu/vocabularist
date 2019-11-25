package com.voltaireu.vocabularist.website.repository;

import com.voltaireu.vocabularist.website.model.Website;
import com.voltaireu.vocabularist.website.model.WebsiteWord;
import com.voltaireu.vocabularist.word.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteWordRepository extends JpaRepository<WebsiteWord, Integer> {
    boolean existsByWebsiteAndWord(Website website, Word word);

    List<WebsiteWord> findAllByWebsiteId(long websiteId);
}
