package com.br.mstravelplan.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class CostumerPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    @ManyToOne
    @JoinColumn(name ="id_policy_coverage")
    private TravelPlan travelPlan;

}
