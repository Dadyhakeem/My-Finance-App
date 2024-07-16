package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.ReceitaDTO;
import com.dev.hakeem.myfinanceapp.entity.Receitas;
import com.dev.hakeem.myfinanceapp.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/receitas")
public class ReceitaController {

    private final ReceitaService service;
    @Autowired
    public ReceitaController(ReceitaService service) {
        this.service = service;
    }
    @PostMapping("/adicionar")
    public ResponseEntity<Receitas> adicionarReceita(@RequestBody @Valid ReceitaDTO receitaDTO){
       Receitas receitas = service.adicionarReceita(receitaDTO);
        return ResponseEntity.ok(receitas);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirReceita(@PathVariable Long id){
         service.excluirReceita(id);
         return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Receitas> atualizarReceita( @RequestBody ReceitaDTO receitaDTO){
        Receitas receitas = service.autualizarReceito(receitaDTO);
        return ResponseEntity.ok(receitas);
    }
}
