package com.br.mstravelplanelegibility.application.exception;

public class CostumerDataNotFoundException extends  Exception {
    public CostumerDataNotFoundException(){
        super("Dados do cliente não encontrados para o CPF informado");
    }
}
