package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.CartaoDTO;
import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.entity.Cartoes;
import com.dev.hakeem.myfinanceapp.repository.DespesaRepository;
import com.dev.hakeem.myfinanceapp.service.CartaoService;
import com.dev.hakeem.myfinanceapp.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
    @Autowired
    private CartaoService service;

    @PostMapping
    public ResponseEntity<CartaoDTO> adicionarCartao(@RequestBody CartaoDTO cartaoDTO){
        CartaoDTO novoCartao = service.adicionarCartao(cartaoDTO);
        return  ResponseEntity.ok(novoCartao);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CartaoDTO> atualizarcartao(@PathVariable Long id, @RequestBody CartaoDTO cartaoDTO){
        cartaoDTO.setId(id);
        CartaoDTO cartaoAtualizado = service.atualizarCartao(cartaoDTO);
        return ResponseEntity.ok(cartaoAtualizado);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CartaoDTO> excluirCartao(@PathVariable Long id){
        service.excluirCartao(id);
        return  ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/despesas")
    public ResponseEntity<DespesaDTO> adicionarDespesa(@PathVariable Long id , @RequestBody DespesaDTO despesaDTO){
        despesaDTO.setCartao_id(id);
        DespesaDTO novoDespesa = service.adicionarDespesa(despesaDTO);
        return ResponseEntity.ok(novoDespesa);
    }
    // Endpoint para buscar um cartão pelo ID
    @GetMapping
    public  ResponseEntity<List<CartaoDTO>> listarCartoes(){
        List<CartaoDTO> cartoes = service.findByAll();
        return ResponseEntity.ok(cartoes);
    }
    // Endpoint para buscar um cartão pelo ID
    @GetMapping("/{id}")
    public  ResponseEntity<CartaoDTO> buscarCartaoPorId(@PathVariable Long id){
        CartaoDTO cartaoDTO = service.findById(id)
                .orElseThrow(()-> new RuntimeException("Cartao nao encontrada"));
        return ResponseEntity.ok(cartaoDTO);
    }



}
