package com.koposovo.animeTest.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.koposovo.animeTest.SpringStageLoader;
import com.koposovo.animeTest.user.Admin;
import com.koposovo.animeTest.user.Analiser;
import com.koposovo.animeTest.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class EntranceController {
    protected static User user = new User();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authSignButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {
        authSignButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            } else {
                System.out.println("Login and Password is Empty");
            }
        });
        loginSignUpButton.setOnAction(event -> {
            try {
                SpringStageLoader.loadScene("signUp");
            } catch (IOException e) {
                System.out.println("Could not open SignUp scene");
            }
        });

    }

    private void loginUser(String loginText, String loginPassword) {
        //найти юзера в бд по логину
        //проверить верный ли пароль
        //каким-то образом зафиксировать какой юзер залогинился
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        int counter = 0;

        if (counter >= 1) {
            //переход к личному кабинету и тестам
            //сделать в зависимости от режима доступа юзера(админ, аналитик или обычный юзер)
            try {
                if (user instanceof Admin)
                    SpringStageLoader.loadScene("AdminView");
                else if (user instanceof Analiser)
                    SpringStageLoader.loadScene("AnaliserInterface");
                else
                    SpringStageLoader.loadScene("UserView");
            } catch (IOException e) {
                System.out.println("Could not open scene after logging in");
            }
        } else {
            Shake userLoginAnim = new Shake(login_field);
            Shake userPassAnim = new Shake(password_field);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }
}

