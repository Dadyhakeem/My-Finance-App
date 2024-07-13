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
    private User user_id;
    @NotNull(message = "nao deve ser nula")
    private TipoConta tipoConta;
    @NotNull(message = "nao deve ser nula")
    private String instituicaoFinanceira;
    private double saldoInicial;
    private  double valor;
}
