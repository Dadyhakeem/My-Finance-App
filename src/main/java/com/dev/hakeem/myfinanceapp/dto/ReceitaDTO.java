package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.enums.CategoriaReceitas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceitaDTO {

    private Long id;
    private LocalDate data_receita;
    private  String descricao;
    private double valor;
    private CategoriaReceitas categoriaReceitas;
    private Conta conta;
    private  Conta conta_id;
}
