package com.voteroid.model;

import java.util.concurrent.atomic.AtomicInteger;

public record Answer(String text, AtomicInteger votes) {}
