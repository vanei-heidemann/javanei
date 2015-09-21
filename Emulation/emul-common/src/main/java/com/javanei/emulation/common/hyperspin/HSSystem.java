package com.javanei.emulation.common.hyperspin;

/**
 * Created by Vanei on 29/08/2015.
 */
public class HSSystem {
    public final String name;
    public final boolean validateCRC;
    public final boolean allowZip;
    public final String dbDir;
    public final String dbFile;
    public final String romsDir;

    public String listname;
    public String lastlistupdate;
    public String listversion;
    public String exporterversion;

    public HSSystem(String name, boolean validateCRC, boolean allowZip) {
        this.name = name;
        this.validateCRC = validateCRC;
        this.allowZip = allowZip;
        this.dbDir = HyperSpinConf.HS_DATABASE_DIR + "/" + name;
        this.dbFile = this.dbDir + "/" + name + ".xml";
        this.romsDir = HyperSpinConf.HS_ROMS_DIR + "/" + name;
    }

    @Override
    public String toString() {
        return "HSSystem{" +
                "name='" + name + '\'' +
                ", validateCRC=" + validateCRC +
                ", allowZip=" + allowZip +
                ", dbFile='" + dbFile + '\'' +
                ", romsDir='" + romsDir + '\'' +
                '}';
    }
}
