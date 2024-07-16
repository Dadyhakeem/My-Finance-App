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
public class DepositoRequestDTO {
    @NotNull(message = "Conta ID não deve ser nulo")
    private Long id;

    @NotNull(message = "Valor não deve ser nulo")
    @Positive(message = "Valor deve ser positivo")
    private Double valor;
}

