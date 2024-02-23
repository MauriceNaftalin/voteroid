package com.voteroid.messagingstompwebsocket;

import com.voteroid.model.Presentation;
import com.voteroid.model.Slide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class PresentationController {

    @Autowired private Presentation presentation;
    @Autowired private SimpMessagingTemplate simpTemplate;

    Logger logger = LoggerFactory.getLogger(PresentationController.class);

    @MessageMapping("/vote")
    public void acceptVote(int answerIndex) {
        logger.debug("acceptVote: " + answerIndex);
        final Slide currentSlide = presentation.getCurrentSlide();
        // answerIndex is 1-indexed, and so is d3
        final int voteCount = currentSlide.answers().get(answerIndex - 1).votes().incrementAndGet();
        simpTemplate.convertAndSend("/topic/votes",
                Map.of("content", Map.of("question", currentSlide.question(), "answerIndex", answerIndex, "voteCount", voteCount)));
    }

    // called at presentation initialisation
    @CrossOrigin
    @PostMapping("/slides")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createSlide(@RequestBody Slide slide) {
        logger.debug("POST /slides: " + slide.question());
        presentation.addSlide(slide);
    }

    // called at presentation initialisation and at the end of a slide transition
    @PutMapping("/current-question-name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setCurrentQuestionName(@RequestBody String currentQuestionName) {
        logger.debug("PUT /current-question-name:  " + currentQuestionName);
        presentation.setCurrentQuestionName(currentQuestionName);
        simpTemplate.convertAndSend("/topic/currentQuestion/", Map.of("content", currentQuestionName));
    }

    // called at client initialisation
    @GetMapping("/slides/current-slide")
    public Slide getCurrentSlide() {
        Slide currentSlide = presentation.getCurrentSlide();
        logger.debug("GET /slides/current-slide: " + currentSlide.question());
        return currentSlide;
    }

    // called by presentation iframe to set up
    @GetMapping("/slides/{questionName}")
    public Slide getSlide(@PathVariable String questionName) {
        logger.debug("GET /slides/{questionName} for " + questionName);
        return presentation.getSlide(questionName);
    }

    // alternative annotations (not sure of the difference)
    // @MessageMapping("/vote") [answer: used to receive STOMP messages sent with prefix set by setApplicationDestinationPrefixes]
    // @SendTo("/topic/votes")
}