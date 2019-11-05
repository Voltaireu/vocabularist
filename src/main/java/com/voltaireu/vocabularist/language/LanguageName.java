package com.voltaireu.vocabularist.language;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum LanguageName {
    ENG("eng"), PL("pl");

    private String value;

    private LanguageName(String value) {
        this.value = value;
    }

    public static List<String> getLanguages() {
        return Stream.of(LanguageName.values())
                .map(LanguageName::name)
                .collect(Collectors.toList());
    }

    public static LanguageName fromValue(String value) {
        for (LanguageName language : values()) {
            if (language.value.equalsIgnoreCase(value)) {
                return language;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }
}
