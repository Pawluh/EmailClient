package com.barosanu.controller.services;

import com.barosanu.model.EmailTreeItem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import java.util.List;

public class FetchFolderService extends Service<Void> {

    private Store store;
    private EmailTreeItem<String> folderRoot;
    private List<Folder> folderList;

    public FetchFolderService(Store store, EmailTreeItem<String> folderRoot, List<Folder> folderList) {
        this.store = store;
        this.folderRoot = folderRoot;
        this.folderList = folderList;
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
            folderList.add(folder);
            EmailTreeItem<String> emailTreeItem = new EmailTreeItem<String>(folder.getName());
            folderRoot.getChildren().add(emailTreeItem); //fetch main folders
            folderRoot.setExpanded(true);
            fetchMessagesonFolder(folder, emailTreeItem);
            addMessageListenerToFolder(folder, emailTreeItem);
            if(folder.getType() == Folder.HOLDS_FOLDERS){// fetch all folders
                Folder[] subFolders = folder.list();// fetch all folders
                handleFolders(subFolders, emailTreeItem); // fetch all folders
            }
        }
    }

    private void addMessageListenerToFolder(Folder folder, EmailTreeItem<String> emailTreeItem) {
        folder.addMessageCountListener(new MessageCountListener() {
            @Override
            public void messagesAdded(MessageCountEvent messageCountEvent) {
                System.out.println("message Added Event " + messageCountEvent);
            }

            @Override
            public void messagesRemoved(MessageCountEvent messageCountEvent) {
                System.out.println("message Remove Event " + messageCountEvent);
            }
        });
    }

    private void fetchMessagesonFolder(Folder folder, EmailTreeItem<String> emailTreeItem) {
        Service fetchMessagesService = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        if(folder.getType() != Folder.HOLDS_FOLDERS){
                            folder.open(Folder.READ_WRITE);
                            int folderSize = folder.getMessageCount();
                            for(int i = folderSize; i> 0; i--){
                              //  System.out.println(folder.getMessage(i).getSubject());
                                emailTreeItem.addEmail(folder.getMessage(i));
                            }
                        }
                        return null;
                    }
                };
            }
        };
        fetchMessagesService.start();
    }
}