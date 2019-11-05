package com.voltaireu.vocabularist.word;

import java.util.Arrays;

public enum PartOfSpeechName {
    NOUN("noun"),
    PRONOUN("pronoun"),
    VERB("verb"),
    ADJECTIVE("adjective"),
    ADVERB("adverb"),
    PREPOSITION("preposition"),
    CONJUNCTION("conjunction"),
    INTERJECTION("interjection");

    private String value;

    PartOfSpeechName(String value) {
        this.value = value;
    }

    public static PartOfSpeechName fromValue(String value) {
        for (PartOfSpeechName partOfSpeech : values()) {
            if (partOfSpeech.value.equalsIgnoreCase(value)) {
                return partOfSpeech;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }
}
