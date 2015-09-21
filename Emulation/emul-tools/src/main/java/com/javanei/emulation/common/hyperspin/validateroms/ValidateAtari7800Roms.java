package com.javanei.emulation.common.hyperspin.validateroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.ValidateRomsUtil;

/**
 * Created by Vanei on 06/09/2015.
 */
public class ValidateAtari7800Roms {
    public static void main(String[] args) {
        try {
            ValidateRomsUtil.validateRoms(HyperSpinSystemConf.HS_System_Atari_7800);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
