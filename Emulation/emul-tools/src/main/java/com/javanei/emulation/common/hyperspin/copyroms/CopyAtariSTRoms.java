package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 04/09/2015.
 */
public class CopyAtariSTRoms {
    public static void main(String[] args) {
        try {
            File srcDir = new File("F:\\Emulation\\ROMs.classificar\\AtariST");
            File dstDir = new File("F:\\Emulation\\HSROMs\\AST");
            CopyRomsUtil.copyRoms(HyperSpinSystemConf.HS_System_Atari_ST, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
