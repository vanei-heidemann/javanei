package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 04/09/2015.
 */
public class CopyN64Roms {
    public static void main(String[] args) {
        try {
            File srcDir = new File("\\\\Vanei-server\\shared\\Malvadeza Hyperspin 1.3\\hyperspin\\emulators\\Nintendo64\\roms");
            File dstDir = new File("F:\\Emulation\\HSROMs\\N64");
            CopyRomsUtil.copyRoms(HyperSpinSystemConf.HS_System_Nintendo_64, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
