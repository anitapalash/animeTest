package com.koposovo.animeTest.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.koposovo.animeTest.SpringStageLoader;
import com.koposovo.animeTest.api.Access;
import com.koposovo.animeTest.api.UserController;
import com.koposovo.animeTest.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;

@Controller
public class EntranceController {
    protected static User user = new User();

    @Autowired
    protected UserController userController;

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
    void logIn(ActionEvent event) {
        String loginText = login_field.getText().trim();
        String loginPassword = password_field.getText().trim();

        if (!loginText.equals("") && !loginPassword.equals("")) {
            loginUser(loginText, loginPassword);
        } else {
            System.out.println("Login and Password is Empty");
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            SpringStageLoader.loadScene("signUp").showAndWait();
        } catch (IOException e) {
            System.out.println("Failed to open signUp Scene");
        }
    }

    private void loginUser(String loginText, String loginPassword) {
        //каким-то образом зафиксировать какой юзер залогинился
        user.setUserName(loginText);
        user.setPassword(loginPassword);

        Long i = Long.parseLong("1");
        while (true) {
            User tempUser = userController.findById(i);
            if (tempUser.getUserName().equals(user.getUserName())) {
                if (tempUser.getPassword().equals(user.getPassword())) {
                    System.out.println("Log in successful");
                    loadEnter();
                    break;
                } else {
                    System.out.println("Wrong password");
                    Shake userLoginAnim = new Shake(login_field);
                    Shake userPassAnim = new Shake(password_field);
                    userLoginAnim.playAnim();
                    userPassAnim.playAnim();
                }
            } else {
                if (!tempUser.getUserName().equals(null))
                    i++;
                else {
                    System.out.println("No such user in database");
                    break;
                }
            }
        }
    }

    private void loadEnter() {
        try {
            UserInterfaceController.setCurrentUser(user);
            Scene currentScene = authSignButton.getScene();
            currentScene.getWindow().hide();
            if (user.getAccess() == Access.ADMIN)
                SpringStageLoader.loadScene("AdminView");
            else if (user.getAccess() == Access.ANALISER)
                SpringStageLoader.loadScene("AnaliserInterface");
            else
                SpringStageLoader.loadScene("UserView");
        } catch (IOException e) {
            System.out.println("Failed to load scene");
        }
    }
}

