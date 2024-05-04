package com.br.mscostumers.application.representation;

import com.br.mscostumers.domain.Costumer;
import lombok.Data;

@Data
public class CostumerSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public Costumer toModel() {
        return new Costumer(cpf, name, age);
    }
}
