package com.javanei.emulation.common.hyperspin.com.javanei.emulation.util;

/**
 * Created by Vanei on 16/09/2015.
 */
public class ROMForNaming {
    public String fullName = "";
    public String name = "";
    public String region;
    public String language;
    public String version;
    public String license;
    public String publisher;
    public String additional;
    public String releaseDate;
    public boolean legal = true;

    @Override
    public String toString() {
        return "ROM {" +
                "fullName='" + fullName + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", language='" + language + '\'' +
                ", version='" + version + '\'' +
                ", license='" + license + '\'' +
                ", publisher='" + publisher + '\'' +
                ", additional='" + additional + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", legal='" + legal + '\'' +
                '}';
    }
}
