package com.barosanu;

import com.barosanu.controller.persistence.PersistenceAccess;
import com.barosanu.controller.persistence.ValidAccount;
import com.barosanu.model.EmailAccount;
import com.barosanu.view.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private PersistenceAccess persistenceAccess = new PersistenceAccess();
    private EmailManager emailManager = new EmailManager();

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(emailManager);
        viewFactory.showLoginWindow();
  //      viewFactory.updateStyles();
    }

    @Override
    public void stop() throws Exception {
        List<ValidAccount> validAccounts = new ArrayList<ValidAccount>();
        for(EmailAccount emailAccount: emailManager.getEmailAccounts()){
            validAccounts.add(new ValidAccount(emailAccount.getAddress(),emailAccount.getPassword()));
        }
        persistenceAccess.savePersistence(validAccounts);
    }
}
