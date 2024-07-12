package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.dto.ReceitaDTO;
import com.dev.hakeem.myfinanceapp.entity.Receitas;
import com.dev.hakeem.myfinanceapp.repository.ReceitaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
@Service
public class ReceitaService {
    @Autowired
    private final ReceitaRepository repository;

    public ReceitaService(ReceitaRepository repository) {
        this.repository = repository;
    }

    public Receitas adicionarReceita(@Valid ReceitaDTO receitaDTO){
        validarReceitaDTO(receitaDTO);
        Receitas receitas = new Receitas();
        receitas.setData_receita(receitaDTO.getData_receita());
        receitas.setValor(receitaDTO.getValor());
        receitas.setDescricao(receitaDTO.getDescricao());
        receitas.setCategoriaReceitas(receitaDTO.getCategoriaReceitas());
        receitas.setConta(receitaDTO.getConta());

        return  repository.save(receitas);
    }

    /**
     * Remove uma receita do repositório pelo seu ID.
     *
     * @param id o ID da receita a ser removida
     * @throws RuntimeException se a receita não for encontrada
     */

    public  void excluirReceita(Long id){
        Optional<Receitas> receitas = repository.findById(id);
            if (!receitas.isPresent()) {
                throw new RuntimeException("Receita nao encontrada");
            }
                repository.delete(receitas.get());
    }

    public  Receitas autualizarReceito(@Valid ReceitaDTO receitaDTO){
        Receitas receitas = repository.findById(receitaDTO.getId())
                .orElseThrow(()-> new RuntimeException("Reeita nao encontrada"));

        receitas.setValor(receitaDTO.getValor());
        receitas.setData_receita(receitaDTO.getData_receita());
        receitas.setDescricao(receitaDTO.getDescricao());
        receitas.setConta(receitaDTO.getConta());
        receitas.setCategoriaReceitas(receitaDTO.getCategoriaReceitas());

        return  repository.save(receitas);

    }

    /**
     * Valida os dados do DTO da despesa.
     *
     * @param receitaDTO o DTO a ser validado
     * @throws IllegalArgumentException se algum dado do DTO for inválido
     */

    private void validarReceitaDTO(ReceitaDTO receitaDTO) {
        if (receitaDTO == null) {
            throw new IllegalArgumentException("ReceitaDTO não pode ser nulo");
        }
        if (!StringUtils.hasText(receitaDTO.getDescricao())) {
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        }
        if (receitaDTO.getValor() <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
        if (receitaDTO.getData_receita() == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        if (receitaDTO.getConta() == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }
        if (receitaDTO.getCategoriaReceitas() == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula");

        }

    }
}
