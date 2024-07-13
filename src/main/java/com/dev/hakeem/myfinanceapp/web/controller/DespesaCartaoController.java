package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.DespesaDecartaoDTO;
import com.dev.hakeem.myfinanceapp.entity.DespesaCartao;
import com.dev.hakeem.myfinanceapp.service.CartaoService;
import com.dev.hakeem.myfinanceapp.service.DespesaCartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/despesas/cartoes")
public class DespesaCartaoController {

    private final DespesaCartaoService service;

    public DespesaCartaoController(DespesaCartaoService service) {
        this.service = service;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<DespesaCartao> adicionarDespesa(@RequestBody DespesaDecartaoDTO despesaDecartaoDTO){
        DespesaCartao despesaCartao = service.adicionarDespesa(despesaDecartaoDTO);
        return ResponseEntity.ok(despesaCartao);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> excluirDespesas(@PathVariable Long id){
        service.excluirDespesas(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/despesas/{id}")
    public  ResponseEntity<DespesaCartao> atualizardespesa(@PathVariable  Long id ,@RequestBody DespesaDecartaoDTO despesaDecartaoDTO ){
        DespesaCartao despesaCartao = service.atualizarDespesa(id,despesaDecartaoDTO);
         return ResponseEntity.ok(despesaCartao);
    }
    @GetMapping("/cartao/{cartaoId}")
    public ResponseEntity<List<DespesaCartao>> TodosAsDespeaDoCartao(@PathVariable Long cartaoId){
         List<DespesaCartao>despesaCartaos = service.listaDasDespesaDoCartao(cartaoId);
         return ResponseEntity.ok(despesaCartaos);
    }
}
