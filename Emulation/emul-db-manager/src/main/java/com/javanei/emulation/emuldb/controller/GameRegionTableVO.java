/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javanei.emulation.emuldb.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jean.heidemann
 */
public class GameRegionTableVO {
    private StringProperty name;
    
    public GameRegionTableVO(String name) {
        this.nameProperty().set(name);
    }
    
    public StringProperty nameProperty() {
        if (this.name == null) {
            this.name = new SimpleStringProperty();
        }
        return this.name;
    }
}
