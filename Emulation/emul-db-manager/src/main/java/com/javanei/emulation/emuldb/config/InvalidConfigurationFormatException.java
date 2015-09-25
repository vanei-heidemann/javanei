package com.javanei.emulation.emuldb.config;

import com.javanei.emulation.common.EmulationException;

/**
 * Created by Vanei on 18/09/2015.
 */
public class InvalidConfigurationFormatException extends EmulationException {

    private static final long serialVersionUID = 1L;

    public InvalidConfigurationFormatException() {
        super("O arquivo de configuração não está em um formato válido");
    }
}
