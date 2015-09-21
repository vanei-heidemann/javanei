package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 03/09/2015.
 */
public class CopyGameBoyRoms {
    public static void main(String[] args) {
        try {
            File srcDir = new File("J:\\hyperspin1.0\\Emulators\\Nintendo Game Boy\\Gameboy rom");
            File dstDir = new File(HyperSpinSystemConf.HS_System_Nintendo_Game_Boy.romsDir);
            CopyRomsUtil.copyRoms(HyperSpinSystemConf.HS_System_Nintendo_Game_Boy, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
