package com.voteroid.model;

import java.util.List;

public record Slide(String question, List<Answer> answers) {}
