package com.javanei.emulation.emuldb.game;

import com.javanei.emulation.common.GameCatalog;
import com.javanei.emulation.common.ThreeStates;
import com.javanei.emulation.common.game.GameDeveloper;
import com.javanei.emulation.common.game.GameLanguage;
import com.javanei.emulation.common.game.GameProtection;
import com.javanei.emulation.common.game.GamePublisher;
import com.javanei.emulation.common.game.GameRegion;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vanei
 */
public class Game implements Serializable, Comparable<Game> {

    private static final long serialVersionUID = 1L;

    private String name;
    private String mainName;
    private String description; //ClrMamePro
    private String version;
    private int year;
    private List<GameLanguage> languages = new LinkedList<>();
    private List<GameRegion> regions = new LinkedList<>();
    private GamePublisher publisher;
    private GameDeveloper developer;
    private GameProtection protection;
    //private ThreeStates alternate = ThreeStates.Unknown; //GoodTools
    private String alternate;
    private String compilation;
    private List<String> complements = new LinkedList<>();
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
    private String proto; //No-Intro
    private String beta; //No-Intro
    private Boolean demo = Boolean.FALSE; //No-Intro
    private Boolean promo = Boolean.FALSE; //No-Intro
    private Boolean sample = Boolean.FALSE; //No-Intro
    private Boolean preview = Boolean.FALSE; //No-Intro
    private final Set<GameFile> roms = new HashSet<>();

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<GameLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<GameLanguage> languages) {
        if (languages != null) {
            this.languages = languages;
        } else {
            this.languages = new LinkedList<>();
        }
    }

    public List<GameRegion> getRegions() {
        return this.regions;
    }

    public void setRegions(List<GameRegion> regions) {
        if (regions != null) {
            this.regions = regions;
        } else {
            this.regions = new LinkedList<>();
        }
    }

    public void addRegion(GameRegion region) {
        this.regions.add(region);
    }

    public GamePublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(GamePublisher publisher) {
        this.publisher = publisher;
    }

    public GameDeveloper getDeveloper() {
        return developer;
    }

    public void setDeveloper(GameDeveloper developer) {
        this.developer = developer;
    }

    public GameProtection getProtection() {
        return protection;
    }

    public void setProtection(GameProtection protection) {
        this.protection = protection;
    }

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    public String getCompilation() {
        return compilation;
    }

    public void setCompilation(String compilation) {
        this.compilation = compilation;
    }

    public List<String> getComplements() {
        return complements;
    }

    public void setComplements(List<String> complements) {
        this.complements = complements;
    }

    public void addComplement(String complement) {
        this.complements.add(complement);
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

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public boolean isProto() {
        return this.proto != null;
    }

    public String getProtoVersion() {
        return this.proto != null && this.proto.length() > 5 ? this.proto.substring(5).trim() : "";
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public boolean isBeta() {
        return this.beta != null;
    }

    public String getBetaVersion() {
        return this.beta != null && this.beta.length() > 4 ? this.beta.substring(4).trim() : "";
    }

    public Boolean getDemo() {
        return demo;
    }

    public void setDemo(Boolean demo) {
        this.demo = demo;
    }

    public Boolean getPromo() {
        return promo;
    }

    public void setPromo(Boolean promo) {
        this.promo = promo;
    }

    public Boolean getSample() {
        return sample;
    }

    public void setSample(Boolean sample) {
        this.sample = sample;
    }

    public Boolean getPreview() {
        return preview;
    }

    public void setPreview(Boolean preview) {
        this.preview = preview;
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
    public int compareTo(Game o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<game");
        this.appendIfNoNull(sb, "name", name);
        this.appendIfNoNull(sb, "mainName", mainName);
        this.appendIfNoNull(sb, "description", description);
        this.appendIfNoNull(sb, "version", version);
        if (this.year > 0) {
            sb.append(" year=\"").append(this.year).append("\"");
        }
        if (this.languages != null && !this.languages.isEmpty()) {
            sb.append(" language=\"");
            for (int i = 0; i < this.languages.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(this.languages.get(i).toString());
            }
            sb.append("\"");
        }
        if (!this.regions.isEmpty()) {
            sb.append(" region=\"");
            for (int i = 0; i < this.regions.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(this.regions.get(i).toString());
            }
            sb.append("\"");
        }
        if (this.publisher != null) {
            sb.append(" publisher=\"").append(this.publisher).append("\"");
        }
        if (this.developer != null) {
            sb.append(" developer=\"").append(this.developer).append("\"");
        }
        if (this.protection != null) {
            sb.append(" protection=\"").append(this.protection).append("\"");
        }
        if (this.catalog != null) {
            sb.append(" catalog=\"").append(this.catalog).append("\"");
        }
        this.appendIfNoNull(sb, "catalogVersion", catalogVersion);
        this.appendIfNoNull(sb, "alternate", alternate);
        this.appendIfNoNull(sb, "compilation", compilation);
        if (!this.complements.isEmpty()) {
            sb.append(" complement=\"");
            for (int i = 0; i < this.complements.size(); i++) {
                if (i > 0) {
                    sb.append("\t");
                }
                sb.append(this.complements.get(i));
            }
            sb.append("\"");
        }
        this.appendIfNoNull(sb, "badDump", badDump);
        this.appendIfNoNull(sb, "fixed", fixed);
        this.appendIfNoNull(sb, "hack", hack);
        this.appendIfNoNull(sb, "overdump", overdump);
        this.appendIfNoNull(sb, "pirate", pirate);
        this.appendIfNoNull(sb, "trained", trained);
        this.appendIfNoNull(sb, "oldTranslation", oldTranslation);
        this.appendIfNoNull(sb, "newerTranslation", newerTranslation);
        this.appendIfNoNull(sb, "verifiedGoodDump", verifiedGoodDump);
        this.appendIfNoNull(sb, "unlicensed", unlicensed);
        this.appendIfNoNull(sb, "proto", proto);
        this.appendIfNoNull(sb, "beta", beta);
        this.appendIfNoNull(sb, "demo", demo);
        this.appendIfNoNull(sb, "promo", promo);
        this.appendIfNoNull(sb, "sample", sample);
        this.appendIfNoNull(sb, "preview", preview);
        sb.append(">\n");
        this.roms.stream().forEach((rom) -> {
            sb.append("\t\t").append(rom.toString());
        });
        sb.append("\t</game>\n");
        return sb.toString();
    }

    private void appendIfNoNull(StringBuilder sb, String name, String value) {
        if (value != null) {
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
        }
    }

    private void appendIfNoNull(StringBuilder sb, String name, ThreeStates value) {
        if (value != null && !value.equals(ThreeStates.Unknown)) {
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
        }
    }

    private void appendIfNoNull(StringBuilder sb, String name, Boolean value) {
        if (null != value && value == Boolean.TRUE) {
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
        }
    }
}
