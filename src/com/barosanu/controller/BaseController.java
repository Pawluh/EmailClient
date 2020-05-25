package com.barosanu.controller;

import com.barosanu.EmailManager;
import com.barosanu.view.ViewFactory;

public abstract class BaseController {

    protected EmailManager emailmanager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(EmailManager emailmanager, ViewFactory viewFactory, String fxmlName) {
        this.emailmanager = emailmanager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName(){
        return fxmlName;
    }
}
