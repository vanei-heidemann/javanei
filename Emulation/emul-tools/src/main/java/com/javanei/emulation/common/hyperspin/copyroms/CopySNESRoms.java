package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.util.HSReadConfigs;
import com.javanei.emulation.common.hyperspin.HSSystem;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 04/09/2015.
 */
public class CopySNESRoms {
    public static void main(String[] args) {
        try {
            HSSystem system = HSReadConfigs.getSystem("Super Nintendo Entertainment System");
            File srcDir = new File("J:\\hyperspin1.0\\Emulators\\Super Nintendo Entertainment System\\HS SNES Roms");
            File dstDir = new File(system.romsDir);
            CopyRomsUtil.copyRoms(system, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
