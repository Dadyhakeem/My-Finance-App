package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
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

    private  Long id;
    private double  valor;
    private LocalDate data;
    private Conta origem;
    private  Conta destino;
    private  Long contaId;

}
