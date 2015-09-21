package com.javanei.emulation.common.hyperspin.validateroms;

import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;
import com.javanei.emulation.common.hyperspin.util.ValidateRomsUtil;

/**
 * Created by Vanei on 06/09/2015.
 */
public class ValidateSegaGameGearRoms {
    public static void main(String[] args) {
        try {
            ValidateRomsUtil.validateRoms(HyperSpinSystemConf.HS_System_Sega_Game_Gear);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
