package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 04/09/2015.
 */
public class CopySegaGameGearRoms {
    public static void main(String[] args) {
        try {
            File srcDir = new File("F:\\Emulation\\ROMs.classificar\\GG");
            File dstDir = new File(HyperSpinSystemConf.HS_System_Sega_Game_Gear.romsDir);
            CopyRomsUtil.copyRoms(HyperSpinSystemConf.HS_System_Sega_Game_Gear, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
