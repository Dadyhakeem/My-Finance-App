package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.DespesaDTO;
import com.dev.hakeem.myfinanceapp.entity.Despesas;
import com.dev.hakeem.myfinanceapp.repository.DespesaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class DespesaService {

    private final DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }
    /**
     * Adiciona uma nova despesa ao repositório.
     *
     * @param despesaDTO o DTO contendo as informações da despesa a ser adicionada
     * @return a despesa adicionada
     * @throws IllegalArgumentException se os dados da despesa forem inválidos
     */

    public Despesas adicionarDespesas( DespesaDTO despesaDTO){
        validarDespesaDTO(despesaDTO);
        Despesas despesas = new Despesas();

        despesas.setValor(despesaDTO.getValor());
        despesas.setData(despesaDTO.getData());
        despesas.setDescriao(despesaDTO.getDescricao());
        despesas.setCategoriaDespesas(despesaDTO.getCategorias());
        despesas.setConta(despesaDTO.getConta());

        return repository.save(despesas);
    }

    /**
     * Remove uma despesa do repositório pelo seu ID.
     *
     * @param id o ID da despesa a ser removida
     * @throws RuntimeException se a despesa não for encontrada
     */

    public  void removerDespesa(Long id ){
        Optional<Despesas> despesas = repository.findById(id);
           if (!despesas.isPresent()){
               throw  new RuntimeException("Despesa nao encontrada");
           }
           repository.delete(despesas.get());
    }

    /**
     * Valida os dados do DTO da despesa.
     *
     * @param despesaDTO o DTO a ser validado
     * @throws IllegalArgumentException se algum dado do DTO for inválido
     */

    private void validarDespesaDTO(DespesaDTO despesaDTO) {
        if (despesaDTO == null) {
            throw new IllegalArgumentException("DespesaDTO não pode ser nulo");
        }
        if (!StringUtils.hasText(despesaDTO.getDescricao())) {
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        }
        if (despesaDTO.getValor() <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
        if (despesaDTO.getData() == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        if (despesaDTO.getConta() == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }
        if (despesaDTO.getCategorias() == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula");
        }
    }

}
