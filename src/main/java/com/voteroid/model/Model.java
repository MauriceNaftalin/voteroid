package com.voteroid.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Model {

    private final Map<String,Slide> slides;
    private String currentQuestionName;
    private final static String NO_QUESTION = "No question";

    public Model() {
        slides = new ConcurrentHashMap<>();
        slides.put(NO_QUESTION, new Slide("No question", List.of()));
    }

    public void addSlide(Slide s) {
        slides.put(s.question(), s);
    }

    public void setCurrentQuestionName(String currentQuestionName) {
        this.currentQuestionName = currentQuestionName;
    }

    public String getCurrentQuestionName() {
        return currentQuestionName;
    }

    public Slide getCurrentSlide() {
        return slides.get(currentQuestionName);
    }

    public Slide getSlide(String questionName) {
        return slides.get(questionName);
    }
}
