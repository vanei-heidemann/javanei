package com.javanei.emulation.emuldb;

import com.javanei.emulation.common.EmulationException;

/**
 * @author Vanei
 */
public class UnknownGameNamePartException extends EmulationException {

    private static final long serialVersionUID = 1L;

    public UnknownGameNamePartException(String parte) {
        super(parte);
    }
}
