package com.javanei.emulation.common.hyperspin.tools;

import com.javanei.emulation.common.hyperspin.HSMainMenu;
import com.javanei.emulation.common.hyperspin.HSSystem;
import com.javanei.emulation.common.hyperspin.util.HSReadConfigs;

import java.io.File;

/**
 * Created by Vanei on 13/09/2015.
 */
public class ClearHSDatabase {
    public static void main(String[] args) {
        try {
            clearDatabase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void clearDatabase() throws Exception {
        // Le o menu.
        System.out.println("Lendo Main Menu");
        HSMainMenu mm = HSReadConfigs.readMainMenu();

        // Processa cada um dos sistemas.
        for (String systemName : mm.games) {
            System.out.println("  Limpando Database do sistema " + systemName);
            HSSystem system = HSReadConfigs.getSystem(systemName);
            File dbDir = new File(system.dbDir);
            File[] files = dbDir.listFiles();
            for (File f : files) {
                if (f.getName().equals(system.name + ".xml")
                        || f.getName().equals("favorites.txt")) {
                    continue;
                }
                f.delete();
            }
        }
    }
}
