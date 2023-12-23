package com.voteroid.messagingstompwebsocket;

import com.voteroid.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired private Model model;
    @Autowired private SimpMessagingTemplate simpTemplate;

    @GetMapping("/vote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@RequestParam(value = "answer") int answer) {
        simpTemplate.convertAndSend("/topic/vote", Map.of("content", answer));
    }

    // alternative annotations (not sure of the difference)
    // @MessageMapping("/vote")
    // @SendTo("/topic/votes")

}
