package com.barosanu.controller.services;

import com.barosanu.model.EmailTreeItem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

public class FetchFolderService extends Service<Void> {

    private Store store;
    private EmailTreeItem<String> folderRoot;

    public FetchFolderService(Store store, EmailTreeItem<String> folderRoot) {
        this.store = store;
        this.folderRoot = folderRoot;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                fetchFolders();
                return null;
            }
        };
    }

    private void fetchFolders() throws MessagingException {
        Folder[] folders = store.getDefaultFolder().list();
        handleFolders(folders, folderRoot);
    }

    private void handleFolders(Folder[] folders, EmailTreeItem<String> folderRoot) throws MessagingException {
        for(Folder folder: folders){
            EmailTreeItem<String> emailTreeItem = new EmailTreeItem<String>(folder.getName());
            folderRoot.getChildren().add(emailTreeItem); //fetch main folders
            folderRoot.setExpanded(true);// fetch all folders
            if(folder.getType() == Folder.HOLDS_FOLDERS){// fetch all folders
                Folder[] subFolders = folder.list();// fetch all folders
                handleFolders(subFolders, emailTreeItem); // fetch all folders
            }
        }
    }
}
