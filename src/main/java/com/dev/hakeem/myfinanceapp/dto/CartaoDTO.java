package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "nao deve ser nula")
    @FutureOrPresent(message = "A data deve ser no presente ou no futuro")
    private LocalDate vencimento;
    @NotNull(message = "nao deve ser nula")
    @FutureOrPresent(message = "A data deve ser no presente ou no futuro")
    private  LocalDate fechamento;
    @NotNull(message = "nao deve ser nulo")
    private  double limite;
    @NotNull(message = "Nao deve ser nula")
    private Conta conta;
    private Long id;
    private Long conta_id;
}
