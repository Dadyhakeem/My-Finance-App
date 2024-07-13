package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.dto.DespesaDecartaoDTO;
import com.dev.hakeem.myfinanceapp.entity.DespesaCartao;
import com.dev.hakeem.myfinanceapp.repository.DespesaCartaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;
@Service
public class DespesaCartaoService {
     @Autowired
    private final DespesaCartaoRepository repository;

    public DespesaCartaoService(DespesaCartaoRepository repository) {
        this.repository = repository;
    }

    /**
     * Adiciona uma nova despesa de cartão.
     *
     * @param dto Objeto contendo os dados da despesa.
     * @return A despesa de cartão salva.
     */

     public DespesaCartao adicionarDespesa(@Valid DespesaDecartaoDTO dto){
        DespesaCartao despesaCartao = new DespesaCartao();

        despesaCartao.setValor(dto.getValor());
        despesaCartao.setData(dto.getData());
        despesaCartao.setDescricao(dto.getDescricao());
        despesaCartao.setCategoriaDespesas(dto.getCategoriaDespesas());
        despesaCartao.setCartoes(dto.getCartoes());

        return  repository.save(despesaCartao);
     }

    /**
     * Exclui uma despesa de cartão pelo ID.
     *
     * @param id O ID da despesa a ser excluída.
     */

     public  void excluirDespesas(Long id){
       Optional<DespesaCartao> dto = repository.findById(id);
       if (!dto.isPresent())
           throw  new RuntimeException("Despesa não encontrada");
       repository.delete(dto.get());




     }

    /**
     * Atualiza uma despesa de cartão existente.
     *
     * @param dto Objeto contendo os novos dados da despesa.
     * @return A despesa de cartão atualizada.
     */


     public  DespesaCartao atualizarDespesa(@PathVariable Long id, @Valid DespesaDecartaoDTO dto){
        DespesaCartao despesaCartao = repository.findById(dto.getId())
                .orElseThrow(()-> new RuntimeException("Despeas nao encontrado"));

         despesaCartao.setValor(dto.getValor());
         despesaCartao.setData(dto.getData());
         despesaCartao.setDescricao(dto.getDescricao());
         despesaCartao.setCategoriaDespesas(dto.getCategoriaDespesas());
         despesaCartao.setCartoes(dto.getCartoes());

         return  repository.save(despesaCartao);
     }

      public List<DespesaCartao> listaDasDespesaDoCartao(Long cartaoId){
         return repository.findBycartaoId(cartaoId);
      }
}
