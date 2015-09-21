package com.javanei.emulation.util;

/**
 * Created by Vanei on 06/09/2015.
 */
public class StringUtil {
    public static final String toStringCRC(long hex) {
        String r = Long.toHexString(hex).toUpperCase();
        while (r.length() < 8) {
            r = "0" + r;
        }
        return r;
    }
}
