package com.javanei.emulation.emuldb.game;

/**
 * @author Vanei
 * @see http://tapes.c64.no/main_tapelist.php?group=all&orderby=publisher
 */
public enum GamePublisher {

    Telarium("Telarium"),
    Trillium("Trillium"),
    Activision("Activision"),
    Lucasfilm("Lucasfilm"),
    Electronic_Arts("Electronic Arts"),
    Ariolasoft("Ariolasoft"),
    Timeworks("Timeworks"),
    Electric_Dreams("Electric Dreams"),
    Palace("Palace"),
    MCM("MCM"),
    Abbex("Abbex"),
    Accolade("Accolade"),
    Alligata("Alligata"),
    Alternative("Alternative"),
    Americana("Americana"),
    Andromeda("Andromeda"),
    Anirog("Anirog"),
    Atlantis("Atlantis"),
    Beyond("Beyond"),
    Bubblebus("Bubblebus"),
    Bulldog("Bulldog"),
    Capcom("Capcom"),
    CDS("CDS"),
    Codemasters("Codemasters"),
    Firebird("Firebird"),
    Silverbird("Silverbird"),
    Melbourne_House("Melbourne House"),
    US_Gold("U.S. Gold"),
    Imagine("Imagine"),
    Hit_Squad("Hit Squad"),
    Rabbit("Rabbit"),
    Zeppelin_Games("Zeppelin Games"),
    Psygnosis("Psygnosis"),
    Micropool("Micropool"),
    Cosmi("Cosmi"),
    Ricochet("Ricochet"),
    Ocean("Ocean"),
    Domark("Domark"),
    Hewson("Hewson"),
    Artworx("Artworx"),
    Unknown("Unknown"),
    MicroValue("MicroValue"),
    Byte_Back("Byte Back")
    ;

    private final String name;

    private GamePublisher(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static GamePublisher fromName(String name) {
        for (GamePublisher region : GamePublisher.values()) {
            if (region.getName().equals(name)) {
                return region;
            }
        }
        throw new IllegalArgumentException(name);
    }
}
