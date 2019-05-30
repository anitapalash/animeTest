package com.koposovo.animeTest;

import javafx.application.Application;

import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
public class AnimeTestApplication extends Application {
	public static void main(final String[] args) {
		SpringApplication.run(AnimeTestApplication.class);
	}

	@Override
	public void start(Stage stage) throws Exception {
        SpringStageLoader.loadMain().show();
	}
}
