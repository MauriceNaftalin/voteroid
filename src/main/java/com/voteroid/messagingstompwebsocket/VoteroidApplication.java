package com.voteroid.messagingstompwebsocket;

import com.voteroid.model.Presentation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VoteroidApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteroidApplication.class, args);
	}

	@Bean
	public Presentation create() {
		return new Presentation();
	}
}
