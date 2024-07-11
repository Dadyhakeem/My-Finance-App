package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContaRequestDTO {

    private TipoConta tipoConta;
    private String instituicaoFinanceira;
    private double saldoInicial;
}
