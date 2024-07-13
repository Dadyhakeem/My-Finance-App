package com.dev.hakeem.myfinanceapp.web.controller;


import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.entity.Despesas;
import com.dev.hakeem.myfinanceapp.service.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    private final DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }
    @PostMapping("/adicionar")
    public ResponseEntity<Despesas> adicionarDespesa(@RequestBody DespesaDTO despesaDTO){
        Despesas despesas = service.adicionarDespesas(despesaDTO);
        return ResponseEntity.ok(despesas);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> excluirDespesa(@PathVariable Long id){
        service.removerDespesa(id);
        return ResponseEntity.noContent().build();
    }

}
