package com.dev.hakeem.myfinanceapp.dto.contadto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferenciaRequestDTO {
    @NotNull(message = "Conta de origem não deve ser nula")
    private Long contaOrigemId;

    @NotNull(message = "Conta de destino não deve ser nula")
    private Long contaDestinoId;

    @NotNull(message = "Valor não deve ser nulo")
    @Positive(message = "Valor deve ser positivo")
    private Double valor;
}

