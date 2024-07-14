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
public class SaqueRequestDTO {
    @NotNull(message = "Conta ID não deve ser nulo")
    private Long contaId;

    @NotNull(message = "Valor não deve ser nulo")
    @Positive(message = "Valor deve ser positivo")
    private Double valor;
}

