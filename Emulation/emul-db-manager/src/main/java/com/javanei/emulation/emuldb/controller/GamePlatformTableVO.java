package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.factory.GamePlatform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vanei
 */
public final class GamePlatformTableVO {

    private StringProperty name;
    private StringProperty validExtension;
    private StringProperty repositoryDir;
    private BooleanProperty multiFile;
    private BooleanProperty allowZip;
    private StringProperty storageFormat;

    public GamePlatformTableVO(GamePlatform plat) {
        this.nameProperty().set(plat.getName());
        this.validExtensionProperty().set(plat.getValidExtension());
        this.repositoryDirProperty().set(plat.getRepositoryDir());
        this.multiFileProperty().set(plat.isMultiFile());
        this.allowZipProperty().set(plat.isAllowZip());
        this.storageFormatProperty().set(plat.getStorageFormat().name());
    }

    public StringProperty nameProperty() {
        if (this.name == null) {
            this.name = new SimpleStringProperty();
        }
        return this.name;
    }

    public StringProperty validExtensionProperty() {
        if (this.validExtension == null) {
            this.validExtension = new SimpleStringProperty();
        }
        return this.validExtension;
    }

    public StringProperty repositoryDirProperty() {
        if (this.repositoryDir == null) {
            this.repositoryDir = new SimpleStringProperty();
        }
        return this.repositoryDir;
    }

    public BooleanProperty multiFileProperty() {
        if (this.multiFile == null) {
            this.multiFile = new SimpleBooleanProperty();
        }
        return this.multiFile;
    }

    public BooleanProperty allowZipProperty() {
        if (this.allowZip == null) {
            this.allowZip = new SimpleBooleanProperty();
        }
        return this.allowZip;
    }

    public StringProperty storageFormatProperty() {
        if (this.storageFormat == null) {
            this.storageFormat = new SimpleStringProperty();
        }
        return this.storageFormat;
    }
}
