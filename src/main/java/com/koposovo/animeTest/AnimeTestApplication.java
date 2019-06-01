package com.koposovo.animeTest;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EntityScan("com.koposovo.animeTest.user")
@EnableJpaRepositories("com.koposovo.animeTest.api")
public class AnimeTestApplication extends Application {

    public static void main(final String[] args) throws IOException {
        ConfigurableApplicationContext cont = SpringApplication.run(AnimeTestApplication.class, args);
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SpringStageLoader.loadMain().show();
    }
}

/*
@ComponentScan({"com.koposovo.animeTest.api"})
@EntityScan("com.koposovo.animeTest.user")
@EnableJpaRepositories("com.koposovo.animeTest.api")
 */