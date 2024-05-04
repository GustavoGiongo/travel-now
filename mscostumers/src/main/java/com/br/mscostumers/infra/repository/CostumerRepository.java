package com.br.mscostumers.infra.repository;

import com.br.mscostumers.domain.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    Optional<Costumer> findByCpf(String cpf);
}
