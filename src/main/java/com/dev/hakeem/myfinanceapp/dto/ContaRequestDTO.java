package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.User;
import com.dev.hakeem.myfinanceapp.enums.TipoConta;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContaRequestDTO {
    private Long id;

    @NotNull(message = "O user_id não deve ser nulo")
    private Long userId;

    @NotNull(message = "O tipo de conta não deve ser nulo")
    private TipoConta tipoConta;

    @NotNull(message = "A instituição financeira não deve ser nula")
    private String instituicaoFinanceira;

    private double saldoInicial;

}
