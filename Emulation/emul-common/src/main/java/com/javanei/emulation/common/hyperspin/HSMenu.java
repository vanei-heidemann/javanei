package com.javanei.emulation.common.hyperspin;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vanei on 13/09/2015.
 */
public class HSMenu {
    public String listname;
    public String lastlistupdate;
    public String listversion = "1.0 Final";
    public String exporterversion = "Javanei HyperSping List Generator";
    public List<HSGame> games = new LinkedList<HSGame>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\r\n");
        sb.append("<menu>\r\n");
        sb.append("\t<header>\t\n");
        sb.append("\t\t<listname>").append(this.listname).append("</listname>\r\n");
        sb.append("\t\t<lastlistupdate>").append(this.lastlistupdate).append("</lastlistupdate>\r\n");
        sb.append("\t\t<listversion>").append(this.listversion).append("</listversion>\r\n");
        sb.append("\t\t<exporterversion>").append(this.exporterversion).append("</exporterversion>\r\n");
        sb.append("\t</header>\r\n");
        for (HSGame game : this.games) {
            sb.append(game.toString());
        }
        sb.append("</menu>");
        return sb.toString();
    }
}
