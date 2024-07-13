package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import jakarta.validation.constraints.Min;
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
public class TransferenciaDTO {

    private Long id;

    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 0, message = "O valor deve ser maior que zero")
    private Double valor;

    @PastOrPresent(message = "A data deve ser no passado ou no presente")
    private LocalDate data;

    @NotNull(message = "A conta de origem não pode ser nula")
    private Conta origem;

    @NotNull(message = "A conta de destino não pode ser nula")
    private Conta destino;
}

