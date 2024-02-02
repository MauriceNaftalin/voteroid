package com.voteroid.messagingstompwebsocket;

import com.voteroid.model.Model;
import com.voteroid.model.Slide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired private Model model;
    @Autowired private SimpMessagingTemplate simpTemplate;

    @PutMapping("/{question}/answers/{answerIndex}/vote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable String question, @PathVariable int answerIndex) {
        simpTemplate.convertAndSend("/topic/vote/" + question, Map.of("content", answerIndex));
    }

    @CrossOrigin
    @PostMapping("/slides")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createSlide(@RequestBody Slide slide) {
        model.addSlide(slide);
    }

    @PutMapping("/current-question-name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setCurrentQuestionName(@RequestBody String currentQuestionName) {
        model.setCurrentQuestionName(currentQuestionName);
        simpTemplate.convertAndSend("/topic/currentQuestion/", Map.of("content", currentQuestionName));
    }

    @GetMapping("/slides/current-slide")
    public Slide getCurrentSlide() {
        return model.getCurrentSlide();
    }

    // called by presentation iframe to set up
    @GetMapping("/slides/{questionName}")
    public Slide getSlide(@PathVariable String questionName) {
        return model.getSlide(questionName);
    }

    // alternative annotations (not sure of the difference)
    // @MessageMapping("/vote")
    // @SendTo("/topic/votes")
}
