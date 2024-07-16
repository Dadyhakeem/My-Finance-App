package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    @Autowired
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<DespesaDTO> adicionarDespesa(@RequestBody DespesaDTO despesaDTO) {
        DespesaDTO despesas = despesaService.adicionarDespesa(despesaDTO);
        return ResponseEntity.ok(despesas);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<DespesaDTO> atualizarDespesa(@PathVariable Long id, @RequestBody DespesaDTO despesaDTO) {
        DespesaDTO despesaAtualizada = despesaService.atualizarDespesa(id, despesaDTO);
        return ResponseEntity.ok(despesaAtualizada);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirDespesa(@PathVariable Long id) {
        despesaService.excluirDespesa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<DespesaDTO> buscarDespesaPorId(@PathVariable Long id) {
        DespesaDTO despesa = despesaService.buscarDespesaPorId(id);
        return ResponseEntity.ok(despesa);
    }

    @GetMapping
    public ResponseEntity<List<DespesaDTO>> buscarTodasDespesas() {
        List<DespesaDTO> despesas = despesaService.buscarTodasDespesas();
        return ResponseEntity.ok(despesas);
    }
}
