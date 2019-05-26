package com.koposovo.animeTest;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan
//@EntityScan("com.koposovo.animeTest.model")
@EnableJpaRepositories

public class AnimeTestApplication extends Application {
	private ConfigurableApplicationContext springContext;
	private Parent rootNode;

	public static void main(final String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(AnimeTestApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/logIn.fxml"));
		//SpringFXMLoader.load("view/logIn.fxml");
		//Scene scene = new Scene((Parent) controller.getView(), 300, 275);
		fxmlLoader.setControllerFactory(springContext::getBean);
	}

	@Override
	public void start(Stage stage) throws Exception {
        SpringStageLoader.loadMain().show();
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
	}
}
