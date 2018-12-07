package com.matthew.java_chess;

import com.matthew.java_chess.entities.InitGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JavaChessApplication {

	@Autowired
	private InitGame initGame;

	public static void main(String[] args) {
		SpringApplication.run(JavaChessApplication.class, args);
	}

	@PostConstruct
	private void init() {
		initGame.initGame();
	}
}
