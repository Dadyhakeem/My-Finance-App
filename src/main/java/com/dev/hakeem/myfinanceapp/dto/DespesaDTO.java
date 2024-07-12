package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.enums.CategoriaDespesas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DespesaDTO {

    private Long id;
    private String descricao;
    private double valor ;
    private Conta conta;
    private CategoriaDespesas categorias;
    private LocalDate data;
    private  Long conta_id;
    private  Long cartao_id;
}
