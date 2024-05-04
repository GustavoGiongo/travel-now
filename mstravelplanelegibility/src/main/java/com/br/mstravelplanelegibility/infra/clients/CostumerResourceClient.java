package com.br.mstravelplanelegibility.infra.clients;

import com.br.mstravelplanelegibility.domain.model.CostumerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscostumers", path="/costumers")
public interface CostumerResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<CostumerData> clientData(@RequestParam("cpf") String cpf);


}

