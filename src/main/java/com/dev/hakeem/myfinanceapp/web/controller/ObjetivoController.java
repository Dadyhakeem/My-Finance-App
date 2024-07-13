package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.ObjetivosDTO;
import com.dev.hakeem.myfinanceapp.entity.Objetivos;
import com.dev.hakeem.myfinanceapp.service.ObjetivoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {

    private  final ObjetivoService service;

    public ObjetivoController(ObjetivoService service) {
        this.service = service;
    }
    @PostMapping("/Criar")
    public ResponseEntity<Objetivos> createObjetivo(@RequestBody @Valid ObjetivosDTO objetivosDTO){
        Objetivos objetivos = service.createObjetivo(objetivosDTO);
        return ResponseEntity.ok(objetivos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirObjetivo(@PathVariable Long id){
        service.excluirObjetivo(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/atualizar")
    public ResponseEntity<Objetivos> atualizarObjetivo(@RequestBody @Valid ObjetivosDTO objetivosDTO){
        Objetivos objetivos = service.atualizar(objetivosDTO);
        return ResponseEntity.ok(objetivos);
    }
}
