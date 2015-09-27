package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.common.EmulationException;

/**
 * @author Vanei
 */
public class UnknownROMFileExtensionException extends EmulationException {

    private static final long serialVersionUID = 1L;

    public UnknownROMFileExtensionException(String extension) {
        //TODO: Traduzir mensagem
        super(extension);
    }
}
