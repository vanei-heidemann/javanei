package com.javanei.emulation.common.hyperspin;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vanei on 13/09/2015.
 */
public class HSMainMenu {
    public List<String> games = new LinkedList<String>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<menu>\r\n");
        for (String game : games) {
            sb.append("\t<game name=\"").append(game).append("\"/>\r\n");
        }
        sb.append("</menu>");
        return sb.toString();
    }
}
