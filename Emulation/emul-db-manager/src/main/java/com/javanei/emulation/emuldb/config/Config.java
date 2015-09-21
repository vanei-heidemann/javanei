package com.javanei.emulation.emuldb.config;

/**
 * Created by Vanei on 18/09/2015.
 */
public class Config {
    /**
     * Diretório base onde ficam salvos os arquivos de ROMs
     */
    private String repositoryBaseDir;
    /**
     * Pasta raiz do hyperspin, quando configurado.
     */
    private String hyperspinBaseDir;
    /**
     * Pasta raiz das ROMs do hyperspin, quando configurado.
     */
    private String hyperspinROMBaseDir;

    public String getRepositoryBaseDir() {
        return repositoryBaseDir;
    }

    public void setRepositoryBaseDir(String repositoryBaseDir) {
        if (repositoryBaseDir == null || repositoryBaseDir.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.repositoryBaseDir = repositoryBaseDir.trim();
    }

    public String getHyperspinBaseDir() {
        return hyperspinBaseDir;
    }

    public void setHyperspinBaseDir(String hyperspinBaseDir) {
        this.hyperspinBaseDir = hyperspinBaseDir;
    }

    public String getHyperspinROMBaseDir() {
        return hyperspinROMBaseDir;
    }

    public void setHyperspinROMBaseDir(String hyperspinROMBaseDir) {
        this.hyperspinROMBaseDir = hyperspinROMBaseDir;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<EmulDB>\n");
        if (this.repositoryBaseDir != null && !this.repositoryBaseDir.isEmpty()) {
            sb.append("\t<repositoryBaseDir>").append(this.repositoryBaseDir).append("</repositoryBaseDir>\n");
        }
        if (this.hyperspinBaseDir != null && !this.hyperspinBaseDir.isEmpty()) {
            sb.append("\t<hyperspinBaseDir>").append(this.hyperspinBaseDir).append("</hyperspinBaseDir>\n");
        }
        if (this.hyperspinROMBaseDir != null && !this.hyperspinROMBaseDir.isEmpty()) {
            sb.append("\t<hyperspinROMBaseDir>").append(this.hyperspinROMBaseDir).append("</hyperspinROMBaseDir>\n");
        }
        sb.append("</EmulDB>\n");
        return sb.toString();
    }
}
