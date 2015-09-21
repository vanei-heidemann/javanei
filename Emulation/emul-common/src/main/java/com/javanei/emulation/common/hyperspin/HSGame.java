package com.javanei.emulation.common.hyperspin;

import java.io.Serializable;

/**
 * Created by Vanei on 29/08/2015.
 */
public class HSGame implements Serializable {
    public String name;
    public String description;
    public String crc;

    public String index;
    public String image;
    public String cloneof;
    public String manufacturer;
    public String year;
    public String genre;
    public String rating;
    public boolean enabled;

    public HSGame() {
    }

    public HSGame(String name, String description, String crc) {
        this.name = name;
        this.description = description;
        this.crc = crc;
    }

    @Override
    public String toString() {
        return "\t<game name=\"" + this.name + "\" index=\"" + this.index + "\" image=\"" + this.image + "\">\r\n"
                + "\t\t<description>" + this.description + "</description>\r\n"
                + "\t\t<cloneof>" + this.cloneof + "</cloneof>\r\n"
                + "\t\t<crc>" + this.crc + "</crc>\r\n"
                + "\t\t<manufacturer>" + this.manufacturer + "</manufacturer>\r\n"
                + "\t\t<year>" + this.year + "</year>\r\n"
                + "\t\t<genre>" + this.genre + "</genre>\r\n"
                + "\t\t<rating>" + this.rating + "</rating>\r\n"
                + "\t\t<enabled>" + (this.enabled ? "Yes" : "No") + "</enabled>\r\n"
                + "\t</game>\r\n";
    }
}
