package com.javanei.emulation.emuldb;

import com.javanei.emulation.common.EmulationException;

/**
 * @author Vanei
 */
public class UnknownTagException extends EmulationException {

    private static final long serialVersionUID = 1L;

    public UnknownTagException(String extension) {
        //TODO: Traduzir mensagem
        super(extension);
    }
}
