package com.barosanu.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconResolver {

    public Node getIconForFolder(String folderName){
        String loweCaseFolderName = folderName.toLowerCase();
        ImageView imageView;
        try{
            if(loweCaseFolderName.contains("@")){
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/email.png")));
            }
            else if(loweCaseFolderName.contains("inbox")){
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/inbox.png")));
            }if(loweCaseFolderName.contains("sent")){
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/sent2.png")));
            }if(loweCaseFolderName.contains("spam")){
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/spam.png")));
            }else{
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/folder.png")));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);
        return imageView;
    }
}
