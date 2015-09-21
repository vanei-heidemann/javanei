package com.javanei.emulation.common;

import java.io.Serializable;

/**
 * Created by Vanei on 18/09/2015.
 */
public abstract class EmulationException extends Exception implements Serializable {
    public EmulationException() {
    }

    public EmulationException(String message) {
        super(message);
    }

    public EmulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmulationException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
