package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.TransferenciaDTO;
import com.dev.hakeem.myfinanceapp.entity.Transferenca;
import com.dev.hakeem.myfinanceapp.service.TransferencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferencaService transferencaService;

    @Autowired
    public TransferenciaController(TransferencaService transferencaService) {
        this.transferencaService = transferencaService;
    }

    @PostMapping("/realizar")
    public ResponseEntity<String> realizarTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) {
        try {
            transferencaService.transferir(transferenciaDTO);
            return ResponseEntity.ok("Transferência realizada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transferenca> obterTransferencia(@PathVariable Long id) {
        Transferenca transferenca = transferencaService.obterTransferenciaPorId(id);
        return ResponseEntity.ok(transferenca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTransferencia(@PathVariable Long id) {
        transferencaService.excluirTransferencia(id);
        return ResponseEntity.noContent().build();
    }
}
