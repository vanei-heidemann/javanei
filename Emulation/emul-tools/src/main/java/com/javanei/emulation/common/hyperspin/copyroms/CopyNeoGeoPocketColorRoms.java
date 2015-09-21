package com.javanei.emulation.common.hyperspin.copyroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.CopyRomsUtil;

import java.io.File;

/**
 * Created by Vanei on 04/09/2015.
 */
public class CopyNeoGeoPocketColorRoms {
    public static void main(String[] args) {
        try {
            File srcDir = new File("F:\\Emulation\\ROMs.classificar\\NGPx");
            File dstDir = new File("F:\\Emulation\\HSROMs\\NGPC");
            CopyRomsUtil.copyRoms(HyperSpinSystemConf.HS_System_SNK_Neo_Geo_Pocket_Color, srcDir, dstDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
