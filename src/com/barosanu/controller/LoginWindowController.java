package com.barosanu.controller;

import com.barosanu.EmailManager;
import com.barosanu.controller.services.LoginService;
import com.barosanu.model.EmailAccount;
import com.barosanu.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends BaseController {

    @FXML
    private TextField emailAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    public LoginWindowController(EmailManager emailmanager, ViewFactory viewFactory, String fxmlName) {
        super(emailmanager, viewFactory, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        System.out.println("Login Button Click!");
        if(fieldsAreValid()){
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailmanager);
            EmailLoginResult emailLoginResult = loginService.login();

            switch (emailLoginResult){
                case SUCCESS:
                    System.out.println("Login successful" + emailAccount);
            }
        }

//        viewFactory.showMainWindow();
//        Stage stage = (Stage) errorLabel.getScene().getWindow();
//        viewFactory.closeStage(stage);
    }

    private boolean fieldsAreValid() {
        if(emailAddressField.getText().isEmpty()){
            errorLabel.setText("Proszę wypełnij pole");
            return false;
        }
        if(passwordField.getText().isEmpty()){
            errorLabel.setText("Proszę wypełnij pole");
            return false;
        }

        return true;
    }

}
