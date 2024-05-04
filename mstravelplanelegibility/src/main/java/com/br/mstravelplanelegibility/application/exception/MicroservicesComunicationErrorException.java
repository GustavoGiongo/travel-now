package com.br.mstravelplanelegibility.application.exception;

import lombok.Getter;

public class MicroservicesComunicationErrorException extends Exception {

    @Getter
    private Integer status;

    public MicroservicesComunicationErrorException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
