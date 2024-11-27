package com.example.band_data.event;

import lombok.Getter;

@Getter
public class UnknownEventException extends RuntimeException{


    public UnknownEventException(String message) {
        super(message);
    }

    public UnknownEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownEventException(Throwable cause) {
        super(cause);
    }
}
