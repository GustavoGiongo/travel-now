package com.br.mstravelplan.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class TravelPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Destination destination;
    private BigDecimal income;
    public TravelPlan(String name, Destination destination, BigDecimal income) {
        this.name = name;
        this.destination = destination;
        this.income = income;
    }
}
