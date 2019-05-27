package com.koposovo.animeTest;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.koposovo.animeTest.model")
@EnableJpaRepositories
public class AnimeTestApplication extends Application {

	public static void main(final String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
        SpringStageLoader.loadMain().show();
	}
}
