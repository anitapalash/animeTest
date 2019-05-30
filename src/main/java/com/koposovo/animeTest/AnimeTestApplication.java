package com.koposovo.animeTest;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableAutoConfiguration
public class AnimeTestApplication extends Application {
	private ConfigurableApplicationContext context;
	private Parent rootNode;

	@Override
	public void init() throws Exception {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AnimeTestApplication.class);
		context = builder.run(getParameters().getRaw().toArray(new String[0]));
	}

	public static void main(final String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
        SpringStageLoader.loadMain().show();
	}


}
