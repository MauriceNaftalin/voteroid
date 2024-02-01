package com.voteroid.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Model {

    private final Map<String,String[]> columnLabels = new ConcurrentHashMap<>();
    private String currentQuestionName;

    public String[] getColumnLabels(String question) {
        return columnLabels.get(question);
    }

    public void setColumnLabels(String question, String[] columnLabels) {
        this.columnLabels.put(question,columnLabels);
    }

    public void setCurrentQuestionName(String currentQuestionName) {
        this.currentQuestionName = currentQuestionName;
    }

    public String getCurrentQuestionName() {
        return currentQuestionName;
    }

}
