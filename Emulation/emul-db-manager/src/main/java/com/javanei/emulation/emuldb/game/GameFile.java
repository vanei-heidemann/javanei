package com.javanei.emulation.emuldb.game;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Vanei
 */
public class GameFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String rom; //clrmamepro
    private long size; //clrmamepro
    private String crc; //clrmamepro
    private String md5; //clrmamepro
    private String sha1; //clrmamepro

    public GameFile() {
    }

    public GameFile(String rom) {
        this.rom = rom;
    }

    public GameFile(String rom, String crc) {
        this.rom = rom;
        this.crc = crc;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.rom);
        hash = 17 * hash + Objects.hashCode(this.crc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameFile other = (GameFile) obj;
        if (!Objects.equals(this.rom, other.rom)) {
            return false;
        }
        if (!Objects.equals(this.crc, other.crc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GameFile {" + "rom=" + rom + ", size=" + size + ", crc=" + crc + ", md5=" + md5 + ", sha1=" + sha1 + '}';
    }
}
