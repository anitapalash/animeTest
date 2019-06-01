package com.koposovo.animeTest.api;

import com.koposovo.animeTest.SpringStageLoader;
import com.koposovo.animeTest.controllers.Shake;
import com.koposovo.animeTest.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormsController {
    protected static User user = new User();

    @Autowired
    UserService userController;

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
    private Button signUpButton;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField group_field;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;


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

        Long i = Long.parseLong("0");
        while (true) {
            User tempUser = userController.getUser(i);
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


    @FXML
    void signUpNewUser(ActionEvent event) {
        String firstName= signUpName.getText();
        String lastName= signUpLastName.getText();
        String userName= login_field.getText();
        String password= password_field.getText();
        String group= group_field.getText();
        String gender;
        if(signUpCheckBoxMale.isSelected())
            gender="Мужской";
        else
            gender="Женский";

        User user = new User(userName, password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGroup(group);
        user.setGender(gender);

        userController.save(user);
    }
}
