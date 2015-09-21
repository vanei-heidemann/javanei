package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 04/09/2015.
 */
public class CopyAtari8BitRoms {
    public static void main(String[] args) {
        try {
            //File srcDir = new File("F:\\Emulation\\ROMs.classificar\\Atari8bit");
            File srcDir = new File("F:\\Emulation\\ROMs.classificar\\Atari800");
            File dstDir = new File("F:\\Emulation\\HSROMs\\A8bit");
            CopyRomsUtil.copyRoms(HyperSpinSystemConf.HS_System_Atari_8Bit, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
