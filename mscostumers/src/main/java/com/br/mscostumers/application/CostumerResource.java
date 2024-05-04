package com.br.mscostumers.application;

import com.br.mscostumers.application.representation.CostumerSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("costumers")
@RequiredArgsConstructor
@Slf4j
public class CostumerResource {

    private final CostumerService service;

    @GetMapping
    public String status() {
        log.info("getting microservices status");
        return "Ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CostumerSaveRequest request){

        var costumer = service.save(request.toModel());
        if(costumer == null){
           return  ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists in the database");
        }
        var headerLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .query("cpf={cpf}").buildAndExpand(costumer.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();

    }

    @GetMapping(params = "cpf")
    public ResponseEntity clientData(@RequestParam("cpf") String cpf) {
        var costumer = service.getByCpf(cpf);
        if(costumer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(costumer);
    }


}
