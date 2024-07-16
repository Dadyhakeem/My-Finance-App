package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Cartoes;
import com.dev.hakeem.myfinanceapp.entity.Despesas;
import com.dev.hakeem.myfinanceapp.enums.CategoriaDespesas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * DTO para representar uma despesa associada a um cartão.
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DespesaDecartaoDTO {

    private Long id;

    private double valor;

    @NotNull(message = "Data não pode ser nula")
    @PastOrPresent(message = "A data deve ser no passado ou no presente")
    private LocalDate data;

    @NotBlank(message = "Descrição não pode estar em branco")
    private String descricao;

    @NotNull(message = "Categoria de despesas não pode ser nula")
    private CategoriaDespesas categoriaDespesas;

    @NotNull(message = "Cartão não pode ser nulo")
    private Long cartoes_id;

    private Long despesa_id; // Renomeado para despesa_id para representar o ID da despesa
}
