package com.voteroid.messagingstompwebsocket;

import com.voteroid.model.Presentation;
import com.voteroid.model.Slide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class PresentationController {

    @Autowired private Presentation presentation;
    @Autowired private SimpMessagingTemplate simpTemplate;

    Logger logger = LoggerFactory.getLogger(PresentationController.class);

    // vote
    @PutMapping("/{question}/answers/{answerIndex}/vote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable String question, @PathVariable int answerIndex) {
        logger.debug("PUT vote: " + answerIndex);
        simpTemplate.convertAndSend("/topic/vote/" + question, Map.of("content", answerIndex));
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
