package com.br.mstravelplanelegibility.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CostumerElegibility {
    private List<EligibleDestination> destinations;
}
