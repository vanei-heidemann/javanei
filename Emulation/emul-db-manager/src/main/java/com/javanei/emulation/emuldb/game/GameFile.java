package com.javanei.emulation.emuldb.game;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Vanei
 */
public class GameFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name; //clrmamepro
    private long size; //clrmamepro
    private String crc; //clrmamepro
    private String md5; //clrmamepro
    private String sha1; //clrmamepro

    public GameFile() {
    }

    public GameFile(String rom) {
        this.name = rom;
    }

    public GameFile(String rom, String crc) {
        this.name = rom;
        this.crc = crc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash = 17 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.crc, other.crc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<rom");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.size > 0) {
            sb.append(" size=\"").append(this.size).append("\"");
        }
        if (this.crc != null) {
            sb.append(" crc=\"").append(this.crc).append("\"");
        }
        if (this.md5 != null) {
            sb.append(" md5=\"").append(this.md5).append("\"");
        }
        if (this.sha1 != null) {
            sb.append(" sha1=\"").append(this.sha1).append("\"");
        }
        sb.append(" />\n");
        return sb.toString();
    }
}
