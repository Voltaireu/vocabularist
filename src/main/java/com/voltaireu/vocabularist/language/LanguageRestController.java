package com.voltaireu.vocabularist.language;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LanguageRestController {

    @GetMapping("/languages")
    @ResponseBody
    public List<String> getSupportedLanguages() {
        return LanguageName.getLanguages();
    }
}
