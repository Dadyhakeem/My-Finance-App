package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.DespesaDecartaoDTO;
import com.dev.hakeem.myfinanceapp.entity.DespesaCartao;
import com.dev.hakeem.myfinanceapp.service.DespesaCartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/despesas/cartoes")
public class DespesaCartaoController  {

    private final DespesaCartaoService service;

    public DespesaCartaoController(DespesaCartaoService service) {
        this.service = service;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<DespesaCartao> adicionarDespesa(@RequestBody DespesaDecartaoDTO despesaDecartaoDTO) {
        DespesaCartao despesaCartao = service.adicionarDespesa(despesaDecartaoDTO);
        return ResponseEntity.ok(despesaCartao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDespesa(@PathVariable Long id) {
        service.excluirDespesa(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/despesas/{id}")
    public ResponseEntity<DespesaCartao> atualizarDespesa(@PathVariable Long id, @RequestBody DespesaDecartaoDTO despesaDecartaoDTO) {
        DespesaCartao despesaCartao = service.atualizarDespesa(id, despesaDecartaoDTO);
        return ResponseEntity.ok(despesaCartao);
    }


}
