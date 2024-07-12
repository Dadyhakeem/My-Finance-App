package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.CartaoDTO;
import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.entity.Cartoes;
import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.entity.Despesas;
import com.dev.hakeem.myfinanceapp.repository.CartaoRepository;
import com.dev.hakeem.myfinanceapp.repository.ContaRepository;
import com.dev.hakeem.myfinanceapp.repository.DespesaRepository;

import java.util.List;
import java.util.Optional;

public class CartaoService {

    private  final CartaoRepository repository;
    private  final ContaRepository contaRepository;
    private  final DespesaRepository despesaRepository;

    public CartaoService(CartaoRepository repository, ContaRepository contaRepository, DespesaRepository despesaRepository) {
        this.repository = repository;
        this.contaRepository = contaRepository;
        this.despesaRepository = despesaRepository;
    }

    /**
     * Adiciona um novo cartão.
     *
     * @param cartaoDTO os dados do cartão a ser adicionado
     * @return o cartão adicionado
     */


    public CartaoDTO adicionarCartao(CartaoDTO cartaoDTO){
        Cartoes cartao = new Cartoes();

        // busca a conta pelo id

        Conta conta = contaRepository.findById(cartaoDTO.getConta_id())
                        .orElseThrow(() -> new RuntimeException("Conta nao encontrada"));

        cartao.setConta(cartaoDTO.getConta());
        cartao.setLimit(cartaoDTO.getLimit());
        cartao.setFechamento(cartaoDTO.getFechamento());
        cartao.setVencimento(cartaoDTO.getVencimento());

        Cartoes savedCartao = repository.save(cartao);


        return mapToDTO(savedCartao);

    }

    /**
     * Atualiza um cartão existente.
     *
     * @param cartaoDTO os dados do cartão a ser atualizado
     * @return o cartão atualizado
     */

    public CartaoDTO atualizarCartao(CartaoDTO cartaoDTO){
        Cartoes cartoes = repository.findById(cartaoDTO.getId())
                .orElseThrow(()-> new RuntimeException("Cartao nao encontrada"));

        Conta conta = contaRepository.findById(cartaoDTO.getConta_id())
                .orElseThrow(() -> new RuntimeException("Conta nao encontrada"));



          cartoes.setLimit(cartaoDTO.getLimit());
          cartoes.setFechamento(cartaoDTO.getFechamento());
          cartoes.setVencimento(cartaoDTO.getVencimento());
          Cartoes updateCartao = repository.save(cartoes);

          return mapToDTO(updateCartao);
    }

    /**
     * Exclui um cartão pelo ID.
     *
     * @param id o ID do cartão a ser excluído
     */

    public  void excluirCartao(Long id){
        Cartoes cartao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartao nao encontrada"));
        repository.delete(cartao);
    }

    /**
     * Adiciona uma despesa a um cartão.
     *
     * @param despesaDTO os dados da despesa a ser adicionada
     * @return a despesa adicionada
     */

    public  DespesaDTO adicionarDespesa(DespesaDTO despesaDTO){
        Cartoes cartoes = repository.findById(despesaDTO.getId())
                .orElseThrow(()-> new RuntimeException("Cartao nao encontrado"));

        Despesas despesas = new Despesas();
        despesas.setConta(despesaDTO.getConta());
        despesas.setDescriao(despesaDTO.getDescricao());
        despesas.setValor(despesaDTO.getValor());
        despesas.setData(despesaDTO.getData());
        despesas.setCategoriaDespesas(despesaDTO.getCategorias());

        Despesas savedDespesas = despesaRepository.save(despesas);
        return  mapToDespesaDTO(savedDespesas);
    }

    public  CartaoDTO mapToDTO(Cartoes cartoes){
        CartaoDTO cartaoDTO =  new CartaoDTO();

        cartaoDTO.setId(cartoes.getId());
        cartaoDTO.setConta_id(cartoes.getConta().getId());
        cartaoDTO.setLimit(cartoes.getLimit());
        cartaoDTO.setFechamento(cartoes.getFechamento());
        cartaoDTO.setVencimento(cartoes.getVencimento());
        return  cartaoDTO;
    }

    public DespesaDTO mapToDespesaDTO(Despesas despesas){
        DespesaDTO despesaDTO = new DespesaDTO();

        despesaDTO.setId(despesas.getId());
        despesaDTO.setCartao_id(despesas.getConta_id().getId());
        despesaDTO.setDescricao(despesas.getDescriao());
        despesaDTO.setValor(despesas.getValor());
        despesaDTO.setData(despesas.getData());
        despesaDTO.setCategorias(despesas.getCategoriaDespesas());

        return despesaDTO;
    }




    public List<Cartoes>findByAll(){
        return repository.findAll();
    }

    public Optional<Cartoes> findById(Long id){
        return repository.findById(id);
    }
}
