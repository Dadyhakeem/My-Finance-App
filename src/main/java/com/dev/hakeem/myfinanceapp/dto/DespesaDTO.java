package com.dev.hakeem.myfinanceapp.dto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.enums.CategoriaDespesas;
import jakarta.validation.constraints.NotBlank;
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
public class DespesaDTO {

    private Long id;
    @NotBlank
    private String descricao;
    @NotBlank(message = "O campo valor nao deve ser nulo ")
    private double valor ;
    @NotNull
    private Conta conta;
    @NotNull
    private CategoriaDespesas categorias;
    @NotNull
    private LocalDate data;
    private  Long conta_id;
    private  Long cartao_id;
}
