package com.javanei.emulation.emuldb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Vanei
 */
public final class GameComplements {

    private static final Map<String, List<String>> complements = new HashMap<>();

    static {
        List<String> c64 = new LinkedList<>();
        c64.add("Program");
        c64.add("MAX");
        c64.add("Super Games");
        c64.add("CCS");
        c64.add("VICE");
        c64.add("Megafun");
        c64.add("HES");
        c64.add("C 64 Games System");
        c64.add("Arcade Classic Pak");
        c64.add("Power Play");
        c64.add("Fun Play");
        // c64 PP
        c64.add("Budget");
        c64.add("RapidLok 1");
        c64.add("RapidLok 5");
        c64.add("RapidLok 6");
        c64.add("Golden Disk 5.91");
        c64.add("Golden Disk 2.92");
        c64.add("Golden Disk 4.92");
        c64.add("Golden Disk Ausgabe 9");
        c64.add("Golden Disk Ausgabe 13");
        c64.add("Single Sided");
        c64.add("C128");
        c64.add("Enhanced");
        c64.add("b-highscores");
        c64.add("b-savegame");
        c64.add("C128-Program");
        c64.add("Golden Disk 4.93");
        c64.add("Addon");
        c64.add("Six Appeal Compilation");
        c64.add("supports Serial Link");
        c64.add("2 sides");
        c64.add("3 sides");
        c64.add("Tape Port Dongle");
        c64.add("New Release");
        c64.add("Software Country");
        c64.add("Software Toolworks");
        c64.add("Golden Disk");
        c64.add("Adventure International");
        c64.add("Newer");
        c64.add("GEOS");
        c64.add("RapidLok");
        c64.add("Rapid Lok");
        c64.add("C128-GEOS");
        c64.add("V-MAX");
        c64.add("V-MAX 2");
        c64.add("Avantage");
        c64.add("b-disk");
        c64.add("Rainbow Collection");
        c64.add("2nd Edition");
        c64.add("Vorpal");
        c64.add("RG");
        c64.add("RS");
        c64.add("MK");
        c64.add("CK");
        c64.add("143.X3");
        c64.add("143.U3");
        c64.add("E260");
        // c64 Tapes
        c64.add("Winners Edition");
        c64.add("Swedish Box");
        c64.add("Second Edition");
        c64.add("Burner"); // Loader
        c64.add("Visiload"); // Loader
        c64.add("Freeload"); // Loader
        c64.add("Bleepload"); // Loader
        c64.add("ROM"); // Loader
        c64.add("Super Pavloda"); // Loader
        c64.add("Wildsave"); // Loader
        c64.add("Novaload"); // Loader
        c64.add("Virgin"); // Loader
        c64.add("CHR"); // Loader
        c64.add("Cyberload"); // Loader
        c64.add("Rasterload"); // Loader
        c64.add("ActivisionWildsave"); // Loader
        c64.add("ActivisionHit-load"); // Loader
        c64.add("Invade-A-Load!"); // Loader
        complements.put("Commodore 64", c64);

        List<String> aST = new ArrayList<>();
        aST.add("Budget - Sizzlers");
        aST.add("Budget - Encore");
        aST.add("Budget - Kixx");
        aST.add("Budget - Action Sixteen");
        aST.add("Budget - Smash 16");
        aST.add("Budget - Klassix");
        aST.add("Budget - 16 Blitz");
        aST.add("Budget - Fox Hits");
        aST.add("Budget - Pocket Soft");
        aST.add("Dual Sided");
        aST.add("Coverdisk - The One - Issue 22");
        aST.add("Amiga + ST");
        aST.add("The Edge");
        aST.add("Pocket Soft");
        complements.put("Atari ST", aST);

        List<String> coleco = new ArrayList<>();
        coleco.add("Adam");
        complements.put("Coleco ColecoVision", coleco);

        List<String> amiga = new ArrayList<>();
        amiga.add("8k");
        amiga.add("64k");
        amiga.add("512KB");
        amiga.add("1MB");
        amiga.add("Addon");
        amiga.add("A500 Plus, A2000");
        amiga.add("A4000");
        amiga.add("A500, A600, A2000");
        amiga.add("A600");
        amiga.add("A1200");
        amiga.add("A3000");
        amiga.add("A4000T");
        amiga.add("A600HD");
        amiga.add("Zork I r88, II v48, III r17");
        amiga.add("Hits for Six - Volume 7");
        amiga.add("Amiga 1200 Bundle - Computer Combat");
        amiga.add("Amiga 1200 Bundle - Amiga Magic");
        amiga.add("Coverdisk");
        amiga.add("Coverdisk - Amiga Action - Issue 30");
        amiga.add("Coverdisk - Amiga Software Extra Nr. 12 - Disk 2 of 2");
        amiga.add("Coverdisk - Amiga Spiele 1");
        amiga.add("Coverdisk - Amiga Fun - Issue 04");
        amiga.add("Coverdisk - Amiga Dream - Issue 22");
        amiga.add("Coverdisk - The One - Issue 59");
        amiga.add("Coverdisk - Sundancer");
        amiga.add("Amiga 500 Bundle - Starter Kit");
        amiga.add("Amiga Fun - Issue 12");
        amiga.add("Amiga + PC");
        complements.put("Commodore Amiga", amiga);
    }

    private GameComplements() {
    }

    public static boolean isComplement(String platform, String tag) {
        List<String> l = complements.get(platform);
        if (l != null) {
            return l.contains(tag);
        }
        return false;
    }
}
