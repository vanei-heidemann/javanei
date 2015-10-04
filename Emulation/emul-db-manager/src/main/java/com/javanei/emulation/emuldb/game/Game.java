package com.javanei.emulation.emuldb.game;

import com.javanei.emulation.common.GameCatalog;
import com.javanei.emulation.common.GameDatFormat;
import com.javanei.emulation.common.ThreeStates;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vanei
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String mainName;
    private String description; //ClrMamePro
    private GameRegion region;
    private ThreeStates alternate = ThreeStates.Unknown; //GoodTools
    private ThreeStates badDump = ThreeStates.Unknown; //GoodTools
    private ThreeStates fixed = ThreeStates.Unknown; //GoodTools
    private ThreeStates hack = ThreeStates.Unknown; //GoodTools
    private ThreeStates overdump = ThreeStates.Unknown; //GoodTools
    private ThreeStates pirate = ThreeStates.Unknown; //GoodTools
    private ThreeStates trained = ThreeStates.Unknown; //GoodTools
    private ThreeStates oldTranslation = ThreeStates.Unknown; // GoodTools
    private ThreeStates newerTranslation = ThreeStates.Unknown; //GoodTools
    private ThreeStates verifiedGoodDump = ThreeStates.Unknown; //GoodTools
    private ThreeStates unlicensed = ThreeStates.Unknown; //GoodTools
    private String catalogVersion;
    private GameCatalog catalog;
    private GameDatFormat datFormat;
    private Boolean proto = Boolean.FALSE; //ClrMamePro
    private Boolean beta = Boolean.FALSE; //ClrMamePro
    private Boolean demo = Boolean.FALSE; //ClrMamePro
    private final Set<GameFile> roms = new HashSet<>();

    //private String language;
    //private String version;
    //private String license;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GameRegion getRegion() {
        return region;
    }

    public void setRegion(GameRegion region) {
        this.region = region;
    }

    public ThreeStates getAlternate() {
        return alternate;
    }

    public void setAlternate(ThreeStates alternate) {
        this.alternate = alternate;
    }

    public ThreeStates getBadDump() {
        return badDump;
    }

    public void setBadDump(ThreeStates badDump) {
        this.badDump = badDump;
    }

    public ThreeStates getFixed() {
        return fixed;
    }

    public void setFixed(ThreeStates fixed) {
        this.fixed = fixed;
    }

    public ThreeStates getHack() {
        return hack;
    }

    public void setHack(ThreeStates hack) {
        this.hack = hack;
    }

    public ThreeStates getOverdump() {
        return overdump;
    }

    public void setOverdump(ThreeStates overdump) {
        this.overdump = overdump;
    }

    public ThreeStates getPirate() {
        return pirate;
    }

    public void setPirate(ThreeStates pirate) {
        this.pirate = pirate;
    }

    public ThreeStates getTrained() {
        return trained;
    }

    public void setTrained(ThreeStates trained) {
        this.trained = trained;
    }

    public ThreeStates getOldTranslation() {
        return oldTranslation;
    }

    public void setOldTranslation(ThreeStates oldTranslation) {
        this.oldTranslation = oldTranslation;
    }

    public ThreeStates getNewerTranslation() {
        return newerTranslation;
    }

    public void setNewerTranslation(ThreeStates newerTranslation) {
        this.newerTranslation = newerTranslation;
    }

    public ThreeStates getVerifiedGoodDump() {
        return verifiedGoodDump;
    }

    public void setVerifiedGoodDump(ThreeStates verifiedGoodDump) {
        this.verifiedGoodDump = verifiedGoodDump;
    }

    public ThreeStates getUnlicensed() {
        return unlicensed;
    }

    public void setUnlicensed(ThreeStates unlicensed) {
        this.unlicensed = unlicensed;
    }

    public String getCatalogVersion() {
        return catalogVersion;
    }

    public void setCatalogVersion(String catalogVersion) {
        this.catalogVersion = catalogVersion;
    }

    public GameCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(GameCatalog catalog) {
        this.catalog = catalog;
    }

    public GameDatFormat getDatFormat() {
        return datFormat;
    }

    public void setDatFormat(GameDatFormat datFormat) {
        this.datFormat = datFormat;
    }

    public Boolean getProto() {
        return proto;
    }

    public void setProto(Boolean proto) {
        this.proto = proto;
    }

    public Boolean getBeta() {
        return beta;
    }

    public void setBeta(Boolean beta) {
        this.beta = beta;
    }

    public Boolean getDemo() {
        return demo;
    }

    public void setDemo(Boolean demo) {
        this.demo = demo;
    }

    public Set<GameFile> getRoms() {
        return roms;
    }

    public void addRom(GameFile rom) {
        if (!this.roms.contains(rom)) {
            this.roms.add(rom);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.catalogVersion);
        hash = 67 * hash + Objects.hashCode(this.catalog);
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
        final Game other = (Game) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.catalogVersion, other.catalogVersion)) {
            return false;
        }
        if (this.catalog != other.catalog) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game {" + "name=" + name + ", mainName=" + mainName + ", description=" + description + ", region=" + region + ", alternate=" + alternate + ", badDump=" + badDump + ", fixed=" + fixed + ", hack=" + hack + ", overdump=" + overdump + ", pirate=" + pirate + ", trained=" + trained + ", oldTranslation=" + oldTranslation + ", newerTranslation=" + newerTranslation + ", verifiedGoodDump=" + verifiedGoodDump + ", unlicensed=" + unlicensed + ", catalogVersion=" + catalogVersion + ", catalog=" + catalog + ", datFormat=" + datFormat + ", roms=" + roms + '}';
    }
}
