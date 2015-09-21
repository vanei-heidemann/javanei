package com.javanei.emulation.emuldb.factory;

import com.javanei.emulation.common.EmulationException;

/**
 * Created by Vanei on 18/09/2015.
 */
public class GamePlatformDoesNotExistsException extends EmulationException {

    private static final long serialVersionUID = 1L;

    public GamePlatformDoesNotExistsException() {
        super("Plataforma não existe");
    }
}
