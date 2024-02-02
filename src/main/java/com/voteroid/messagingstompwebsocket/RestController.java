package com.voteroid.messagingstompwebsocket;

import com.voteroid.model.Model;
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

    //https://stackoverflow.com/questions/4596351/binding-a-list-in-requestparam
    //https://stackoverflow.com/questions/11889997/how-to-send-an-array-in-url-request
    @GetMapping("/{question}/setColumnLabels")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setColumnLabels(@PathVariable String question, @RequestParam(value = "labels") String concatenatedLabels) {
        model.createSlide(question,concatenatedLabels.split(";"));
    }

    @PutMapping("/current-question-name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setCurrentQuestionName(@RequestBody String currentQuestionName) {
        model.setCurrentQuestionName(currentQuestionName);
        simpTemplate.convertAndSend("/topic/currentQuestion/", Map.of("content", currentQuestionName));
    }

    @GetMapping("/current-question-name")
    public String getCurrentQuestionName() {
        return model.getCurrentQuestionName();
    }

    // called by presentation iframe to set up
    @GetMapping("/slides/{questionName}/answer-texts")
    public List<String> getColumns(@PathVariable String questionName) {
        return model.getColumnLabels(questionName);
    }

    // alternative annotations (not sure of the difference)
    // @MessageMapping("/vote")
    // @SendTo("/topic/votes")
}
