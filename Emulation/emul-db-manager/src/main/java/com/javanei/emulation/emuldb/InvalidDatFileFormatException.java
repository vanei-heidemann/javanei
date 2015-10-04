package com.javanei.emulation.emuldb;

import com.javanei.emulation.common.EmulationException;

/**
 * @author Vanei
 */
public class InvalidDatFileFormatException extends EmulationException {

    private static final long serialVersionUID = 1L;

    public InvalidDatFileFormatException(String format) {
        super(format);
    }
}
