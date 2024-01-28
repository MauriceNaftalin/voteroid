package com.voteroid.messagingstompwebsocket;

import com.voteroid.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired private Model model;
    @Autowired private SimpMessagingTemplate simpTemplate;

    @GetMapping("/{question}/vote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable String question, @RequestParam(value = "answer") int answer) {
        simpTemplate.convertAndSend("/topic/vote/" + question, Map.of("content", answer));
    }

    //https://stackoverflow.com/questions/4596351/binding-a-list-in-requestparam
    //https://stackoverflow.com/questions/11889997/how-to-send-an-array-in-url-request
    @GetMapping("/{question}/setColumnLabels")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setColumnLabels(@PathVariable String question, @RequestParam(value = "labels") String concatenatedLabels) {
        model.setColumnLabels(question,concatenatedLabels.split(";"));
    }

    @GetMapping("/{question}/getColumnLabels")
    public String[] getColumnLabels(@PathVariable String question) {
        return model.getColumnLabels(question);
    }

    // alternative annotations (not sure of the difference)
    // @MessageMapping("/vote")
    // @SendTo("/topic/votes")
}
