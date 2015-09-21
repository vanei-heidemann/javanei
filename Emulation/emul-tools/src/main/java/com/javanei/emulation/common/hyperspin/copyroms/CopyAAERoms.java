package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HSSystem;
import com.javanei.emulation.common.hyperspin.util.HSReadConfigs;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 03/09/2015.
 */
public class CopyAAERoms {
    public static void main(String[] args) {
        try {
            //TODO: NAO FUNCIONA, USA ROMS MAME
            File srcDir = new File("J:\\hyperspin1.0\\Emulators\\AAE\\AAE\\roms");
            HSSystem system = HSReadConfigs.getSystem("AAE");
            File dstDir = new File(system.romsDir);
            CopyRomsUtil.copyRoms(system, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
