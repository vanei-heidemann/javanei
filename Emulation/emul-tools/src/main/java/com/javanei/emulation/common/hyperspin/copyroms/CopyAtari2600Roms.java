package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 04/09/2015.
 */
public class CopyAtari2600Roms {
    public static void main(String[] args) {
        try {
            File srcDir = new File("J:\\hyperspin1.0\\Emulators\\Atari 2600\\MESS\\roms");
            File dstDir = new File(HyperSpinSystemConf.HS_System_Atari_2600.romsDir);
            CopyRomsUtil.copyRoms(HyperSpinSystemConf.HS_System_Atari_2600, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
