package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.DespesaDecartaoDTO;
import com.dev.hakeem.myfinanceapp.entity.DespesaCartao;
import com.dev.hakeem.myfinanceapp.repository.DespesaCartaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DespesaCartaoService  {

    private final DespesaCartaoRepository repository;

    @Autowired
    public DespesaCartaoService(DespesaCartaoRepository repository) {
        this.repository = repository;
    }

    /**
     * Adiciona uma nova despesa de cartão.
     *
     * @param dto Objeto contendo os dados da despesa.
     * @return A despesa de cartão salva.
     */
    public DespesaCartao adicionarDespesa(@Valid DespesaDecartaoDTO dto) {
        DespesaCartao despesaCartao = new DespesaCartao();

        despesaCartao.setValor(dto.getValor());
        despesaCartao.setData(dto.getData());
        despesaCartao.setDescricao(dto.getDescricao());
        despesaCartao.setCategoriaDespesas(dto.getCategoriaDespesas());
        despesaCartao.setId(dto.getDespesa_id());

        return repository.save(despesaCartao);
    }

    /**
     * Exclui uma despesa de cartão pelo ID.
     *
     * @param id O ID da despesa a ser excluída.
     */
    public void excluirDespesa(Long id) {
        Optional<DespesaCartao> optionalDespesaCartao = repository.findById(id);
        if (optionalDespesaCartao.isEmpty()) {
            throw new RuntimeException("Despesa não encontrada");
        }
        repository.delete(optionalDespesaCartao.get());
    }

    /**
     * Atualiza uma despesa de cartão existente.
     *
     * @param id  O ID da despesa a ser atualizada.
     * @param dto Objeto contendo os novos dados da despesa.
     * @return A despesa de cartão atualizada.
     */
    public DespesaCartao atualizarDespesa(Long id, @Valid DespesaDecartaoDTO dto) {
        DespesaCartao despesaCartao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        despesaCartao.setValor(dto.getValor());
        despesaCartao.setData(dto.getData());
        despesaCartao.setDescricao(dto.getDescricao());
        despesaCartao.setCategoriaDespesas(dto.getCategoriaDespesas());
        despesaCartao.setId(dto.getId());

        return repository.save(despesaCartao);
    }



    /**
     * Lista todas as despesas de um cartão específico.
     *
     * @param cartaoId O ID do cartão para o qual se deseja listar as despesas.
     * @return Lista de despesas do cartão especificado.
     */
    
    
}
