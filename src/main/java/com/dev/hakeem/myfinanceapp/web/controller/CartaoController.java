package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.dto.DespesaDecartaoDTO;
import com.dev.hakeem.myfinanceapp.dto.cartaodto.AdicionarCartaoDTO;
import com.dev.hakeem.myfinanceapp.dto.cartaodto.UpdateCartaoDTO;
import com.dev.hakeem.myfinanceapp.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartoes")
public class CartaoController {

    private final CartaoService service;

    @Autowired
    public CartaoController(CartaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AdicionarCartaoDTO> adicionarCartao(@RequestBody AdicionarCartaoDTO adicionarCartaoDTO) {
        AdicionarCartaoDTO novoCartao = service.adicionarCartao(adicionarCartaoDTO);
        return ResponseEntity.ok().body(novoCartao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCartaoDTO> atualizarCartao(@PathVariable Long id, @RequestBody UpdateCartaoDTO cartaoDTO) {
        cartaoDTO.setId(id);
        UpdateCartaoDTO cartaoAtualizado = service.atualizarCartao(cartaoDTO);
        return ResponseEntity.ok(cartaoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCartao(@PathVariable Long id) {
        service.excluirCartao(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/despesas/adicionar")
    public ResponseEntity<DespesaDTO> adicionarDespesa(@RequestBody DespesaDecartaoDTO despesaDTO) {
        DespesaDTO despesa = service.adicionarDespesa(despesaDTO);
        return ResponseEntity.ok(despesa);
    }

    @GetMapping
    public ResponseEntity<List<UpdateCartaoDTO>> listarCartoes() {
        List<UpdateCartaoDTO> cartoes = service.findAll();
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateCartaoDTO> buscarCartaoPorId(@PathVariable Long id) {
        UpdateCartaoDTO cartaoDTO = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
        return ResponseEntity.ok(cartaoDTO);
    }
}
