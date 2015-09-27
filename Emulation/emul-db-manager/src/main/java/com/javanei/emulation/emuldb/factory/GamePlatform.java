package com.javanei.emulation.emuldb.factory;

import java.io.Serializable;

/**
 * Sistema emulado.
 * <p/>
 * Created by Vanei on 18/09/2015.
 */
public final class GamePlatform implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String validExtension = "*";
    private String repositoryDir = "./{PlatformName}";
    private boolean multiFile = false;
    private boolean allowZip = true;
    private StorageFormat storageFormat = StorageFormat.zip;

    protected GamePlatform() {
    }

    protected GamePlatform(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getValidExtension() {
        return validExtension;
    }

    public void setValidExtension(String validExtension) {
        this.validExtension = validExtension;
    }

    public String getRepositoryDir() {
        return repositoryDir;
    }

    protected void setRepositoryDir(String repositoryDir) {
        this.repositoryDir = repositoryDir;
    }

    public boolean isMultiFile() {
        return multiFile;
    }

    protected void setMultiFile(boolean multiFile) {
        this.multiFile = multiFile;
    }

    public boolean isAllowZip() {
        return allowZip;
    }

    protected void setAllowZip(boolean allowZip) {
        this.allowZip = allowZip;
    }

    public StorageFormat getStorageFormat() {
        return storageFormat;
    }

    protected void setStorageFormat(StorageFormat storageFormat) {
        this.storageFormat = storageFormat;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<platform name=\"").append(this.name).append("\"");
        sb.append(" validExtension=\"").append(this.validExtension).append("\"");
        sb.append(" repositoryDir=\"").append(this.repositoryDir).append("\"");
        sb.append(" multiFile=\"").append(this.multiFile).append("\"");
        sb.append(" allowZip=\"").append(this.allowZip).append("\"");
        sb.append(" storageFormat=\"").append(this.storageFormat).append("\">");
        sb.append("</platform>");
        return sb.toString();
    }
}
