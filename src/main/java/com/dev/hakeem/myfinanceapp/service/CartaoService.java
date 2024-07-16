package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.dto.DespesaDecartaoDTO;
import com.dev.hakeem.myfinanceapp.dto.cartaodto.AdicionarCartaoDTO;
import com.dev.hakeem.myfinanceapp.dto.cartaodto.UpdateCartaoDTO;
import com.dev.hakeem.myfinanceapp.entity.Cartoes;
import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.entity.Despesas;
import com.dev.hakeem.myfinanceapp.repository.CartaoRepository;
import com.dev.hakeem.myfinanceapp.repository.ContaRepository;
import com.dev.hakeem.myfinanceapp.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartaoService {

    private final CartaoRepository repository;
    private final ContaRepository contaRepository;
    private final DespesaRepository despesaRepository;

    @Autowired
    public CartaoService(CartaoRepository repository, ContaRepository contaRepository, DespesaRepository despesaRepository) {
        this.repository = repository;
        this.contaRepository = contaRepository;
        this.despesaRepository = despesaRepository;
    }

    public AdicionarCartaoDTO adicionarCartao(AdicionarCartaoDTO adicionarCartaoDTO) {
        Conta conta = contaRepository.findById(adicionarCartaoDTO.getContaid())
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        Cartoes cartao = new Cartoes();
        cartao.setConta(conta);
        cartao.setLimite(adicionarCartaoDTO.getLimite());
        cartao.setFechamento(adicionarCartaoDTO.getFechamento());
        cartao.setVencimento(adicionarCartaoDTO.getVencimento());

        Cartoes savedCartao = repository.save(cartao);

        return new AdicionarCartaoDTO(
                savedCartao.getId(),
                savedCartao.getFechamento(),
                savedCartao.getLimite(),
                savedCartao.getVencimento(),
                savedCartao.getConta().getId()
        );
    }

    public UpdateCartaoDTO atualizarCartao(UpdateCartaoDTO cartaoDTO) {
        Optional<Cartoes> cartaoExistente = repository.findById(cartaoDTO.getId());
        if (cartaoExistente.isPresent()) {
            Cartoes cartao = cartaoExistente.get();
            cartao.setLimite(cartaoDTO.getLimite());
            cartao.setFechamento(cartaoDTO.getFechamento());
            cartao.setVencimento(cartaoDTO.getVencimento());

            Cartoes updatedCartao = repository.save(cartao);
            return mapToDTO(updatedCartao);
        } else {
            throw new IllegalArgumentException("Cartão não encontrado");
        }
    }

    public void excluirCartao(Long id) {
        Cartoes cartao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
        repository.delete(cartao);
    }

    public DespesaDTO adicionarDespesa(DespesaDecartaoDTO despesaDTO) {
        Cartoes cartao = repository.findById(despesaDTO.getDespesa_id())
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        Despesas despesa = new Despesas();
        despesa.setId(despesaDTO.getDespesa_id());
        despesa.setDescriao(despesaDTO.getDescricao());
        despesa.setValor(despesaDTO.getValor());
        despesa.setData(despesaDTO.getData());
        despesa.setCategoriaDespesas(despesaDTO.getCategoriaDespesas());

        Despesas savedDespesa = despesaRepository.save(despesa);
        return mapToDespesaDTO(savedDespesa);
    }

    public UpdateCartaoDTO mapToDTO(Cartoes cartao) {
        UpdateCartaoDTO cartaoDTO = new UpdateCartaoDTO();
        cartaoDTO.setId(cartao.getId());
        cartaoDTO.setLimite(cartao.getLimite());
        cartaoDTO.setFechamento(cartao.getFechamento());
        cartaoDTO.setVencimento(cartao.getVencimento());
        cartaoDTO.setConta(cartao.getConta());
        return cartaoDTO;
    }

    public DespesaDTO mapToDespesaDTO(Despesas despesa) {
        DespesaDTO despesaDTO = new DespesaDTO();
        despesaDTO.setId(despesa.getId());
        despesaDTO.setDescricao(despesa.getDescriao());
        despesaDTO.setValor(despesa.getValor());
        despesaDTO.setData(despesa.getData());
        despesaDTO.setCategorias(despesa.getCategoriaDespesas());
        return despesaDTO;
    }

    public List<UpdateCartaoDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UpdateCartaoDTO> findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO);
    }
}
