package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.enums.CategoriaReceitas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceitaDTO {

    private Long id;
    @PastOrPresent(message =  "A data deve ser o passado ou no presente" )
    private LocalDate data_receita;
    @NotNull
    private  String descricao;
    @NotNull
    private double valor;
    @NotNull
    private CategoriaReceitas categoriaReceitas;
    @NotNull(message = "Conta não pode ser nula")
    private Conta conta;

}
