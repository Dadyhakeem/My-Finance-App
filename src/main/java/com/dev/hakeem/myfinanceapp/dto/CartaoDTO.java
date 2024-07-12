package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartaoDTO {

    private LocalDate vencimento;
    private  LocalDate fechamento;
    private  double limite;
    private Conta conta;
    private Long id;
    private Long conta_id;
}
