/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javanei.emulation.emuldb;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vanei
 */
public final class MessageFactory {

    private static final MessageFactory instance = new MessageFactory();
    private StringProperty message = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();

    private MessageFactory() {
    }

    public static synchronized MessageFactory getInstance() {
        return instance;
    }

    public StringProperty messageProperty() {
        return this.message;
    }

    public StringProperty typeProperty() {
        return this.type;
    }

    public final void showInfoMessage(String msg) {
        this.typeProperty().set("INFO");
        this.messageProperty().set(msg);
    }

    public void showErrorMesssage(String msg) {
        this.typeProperty().set("ERROR");
        this.messageProperty().set(msg);
    }

    public void showErrorMesssage(Exception ex) {
        this.typeProperty().set("ERROR");
        this.messageProperty().set(ex.getLocalizedMessage());
    }
}
