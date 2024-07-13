package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.ContaRequestDTO;
import com.dev.hakeem.myfinanceapp.dto.TransferenciaDTO;
import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.service.ContaService;
import com.dev.hakeem.myfinanceapp.service.TransferencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService service;
    private final TransferencaService transferencaService;

     @Autowired
    public ContaController(ContaService service, TransferencaService transferencaService) {
        this.service = service;
        this.transferencaService = transferencaService;
    }

    @PostMapping
    public ResponseEntity<Conta> createConta(@RequestBody @Validated ContaRequestDTO requestDTO){
        Conta novaConta = service.createConta(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }
    @PostMapping("/depositar")
    public  ResponseEntity<Conta> deposito(@RequestBody @Validated ContaRequestDTO requestDTO){
        Conta obj = service.depositar(requestDTO);
        return ResponseEntity.ok(obj);
    }

    /**
     * Endpoint para realizar um saque em uma conta existente.
     *
     * @param id    ID da conta da qual o saque será realizado
     * @param valor Valor a ser sacado
     * @return Resposta vazia com status HTTP 204 (No Content) se bem-sucedido
     */
    @PostMapping("/sacar/{id}")
    public  ResponseEntity<Void>  sacar (@PathVariable Long id,@RequestParam double valor ){
        Conta conta = service.buscarPorId(id);
        service.sacar(conta,valor);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/transferir")
    public ResponseEntity<Void> transferir(@RequestBody @Validated TransferenciaDTO transferenciaDTO){
        transferencaService.transferir(transferenciaDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Conta> buscarPorId(@PathVariable  Long id){
        Conta conta = service.buscarPorId(id);
        return ResponseEntity.ok(conta);
    }
    @GetMapping
    public  ResponseEntity<List<Conta>> listarACcontas(){
        List<Conta> contas = service.listarContas();
        return  ResponseEntity.ok(contas);
    }
}
