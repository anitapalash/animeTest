package com.koposovo.animeTest.controllers;

import com.koposovo.animeTest.api.UserController;
import com.koposovo.animeTest.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SignUpController {
    @Autowired
    protected UserController userController;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

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
