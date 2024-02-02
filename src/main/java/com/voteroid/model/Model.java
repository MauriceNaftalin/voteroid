package com.voteroid.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Model {

    private final Map<String,List<Answer>> slides = new ConcurrentHashMap<>();
    private String currentQuestionName;

    public void createSlide(String question, String[] answerTexts) {
        List<Answer> columns = Arrays.stream(answerTexts)
                .map(l -> new Answer(l,new AtomicInteger())).toList();
        slides.put(question,columns);
    }

    public void setCurrentQuestionName(String currentQuestionName) {
        this.currentQuestionName = currentQuestionName;
    }

    public String getCurrentQuestionName() {
        return currentQuestionName;
    }

    public List<String> getColumnLabels(String questionName) {
        return slides.get(questionName).stream().map(Answer::text).toList();
    }
}
