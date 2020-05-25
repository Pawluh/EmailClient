package com.barosanu.controller;

import com.barosanu.EmailManager;
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
        System.out.println("Login Button Clcik!");
        viewFactory.showMainWindow();
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
