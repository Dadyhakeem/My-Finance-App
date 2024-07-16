package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.entity.Cartoes;
import com.dev.hakeem.myfinanceapp.entity.Despesas;
import com.dev.hakeem.myfinanceapp.repository.CartaoRepository;
import com.dev.hakeem.myfinanceapp.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final CartaoRepository cartaoRepository;

    @Autowired
    public DespesaService(DespesaRepository despesaRepository, CartaoRepository cartaoRepository) {
        this.despesaRepository = despesaRepository;
        this.cartaoRepository = cartaoRepository;
    }

    public DespesaDTO adicionarDespesa(DespesaDTO despesaDTO) {
        Cartoes cartoes = cartaoRepository.findById(despesaDTO.getContaid().getId())
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        Despesas despesa = new Despesas();
        despesa.setDescriao(despesaDTO.getDescricao());
        despesa.setValor(despesaDTO.getValor());
        despesa.setData(despesaDTO.getData());
        despesa.setCategoriaDespesas(despesaDTO.getCategorias());
        despesa.setContaid(despesaDTO.getContaid());

        Despesas savedDespesa = despesaRepository.save(despesa);
        return mapToDespesaDTO(savedDespesa);
    }

    public DespesaDTO atualizarDespesa(Long id, DespesaDTO despesaDTO) {
        Despesas despesaExistente = despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        despesaExistente.setDescriao(despesaDTO.getDescricao());
        despesaExistente.setValor(despesaDTO.getValor());
        despesaExistente.setData(despesaDTO.getData());
        despesaExistente.setCategoriaDespesas(despesaDTO.getCategorias());

        Despesas updatedDespesa = despesaRepository.save(despesaExistente);
        return mapToDespesaDTO(updatedDespesa);
    }

    public void excluirDespesa(Long id) {
        Despesas despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
        despesaRepository.delete(despesa);
    }

    public DespesaDTO buscarDespesaPorId(Long id) {
        Despesas despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
        return mapToDespesaDTO(despesa);
    }

    public List<DespesaDTO> buscarTodasDespesas() {
        return despesaRepository.findAll().stream()
                .map(this::mapToDespesaDTO)
                .collect(Collectors.toList());
    }

    private DespesaDTO mapToDespesaDTO(Despesas despesa) {
        DespesaDTO despesaDTO = new DespesaDTO();
        despesaDTO.setId(despesa.getId());
        despesaDTO.setDescricao(despesa.getDescriao());
        despesaDTO.setValor(despesa.getValor());
        despesaDTO.setData(despesa.getData());
        despesaDTO.setCategorias(despesa.getCategoriaDespesas());
        despesaDTO.setContaid(despesa.getContaid());
        return despesaDTO;
    }
}
