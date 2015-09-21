package com.javanei.emulation.emuldb.factory;

import com.javanei.emulation.common.EmulationException;

/**
 * Created by Vanei on 18/09/2015.
 */
public class GamePlatformAlreadyExistsException extends EmulationException {

    private static final long serialVersionUID = 1L;

    public GamePlatformAlreadyExistsException() {
        super("Plataforma já existe");
    }
}
