package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import jakarta.validation.constraints.NotBlank;
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

    private  Long id;
    @NotBlank
    private double  valor;
    @PastOrPresent(message = "A data deve ser o passado ou no presente")
    private LocalDate data;
    @NotBlank
    private Conta origem;
    @NotBlank
    private  Conta destino;
    private  Long contaId;

}
