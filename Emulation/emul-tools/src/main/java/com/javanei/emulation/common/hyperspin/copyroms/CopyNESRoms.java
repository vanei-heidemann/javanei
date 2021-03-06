package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 04/09/2015.
 */
public class CopyNESRoms {
    public static void main(String[] args) {
        try {
            File srcDir = new File("F:\\Emulation\\ROMs.classificar\\NES");
            File dstDir = new File("F:\\Emulation\\HSROMs\\NES");
            CopyRomsUtil.copyRoms(HyperSpinSystemConf.HS_System_Nintendo_Entertainment_System, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
