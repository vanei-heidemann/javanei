package com.javanei.emulation.common.hyperspin.validateroms;

import com.javanei.emulation.common.hyperspin.util.ValidateRomsUtil;
import com.javanei.emulation.common.hyperspin.HyperSpinSystemConf;

/**
 * Created by Vanei on 06/09/2015.
 */
public class ValidateNESRoms {
    public static void main(String[] args) {
        try {
            ValidateRomsUtil.validateRoms(HyperSpinSystemConf.HS_System_Nintendo_Entertainment_System);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
