package com.voteroid.messagingstompwebsocket;

import com.voteroid.model.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessagingStompWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingStompWebsocketApplication.class, args);
	}

	// currently not in use, but this would represent the state of the
	// application if/when it has a state
	@Bean
	public Model main() {
		return new Model();
	}
}
