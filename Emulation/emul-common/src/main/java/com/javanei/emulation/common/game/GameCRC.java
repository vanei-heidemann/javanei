package com.javanei.emulation.common.game;

/**
 * Created by Vanei on 18/09/2015.
 */
public class GameCRC {
    private String name;
    private String crc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    @Override
    public String toString() {
        return "GameCRC{" +
                "name='" + name + '\'' +
                ", crc='" + crc + '\'' +
                '}';
    }
}
