package com.dev.hakeem.myfinanceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObjetivosDTO {

    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private LocalDate data_alvo;

    @NotNull
    private double meta;

    @NotNull
    private double valor_inicial;
    private  Long user_id;
}
