package com.dev.hakeem.myfinanceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
    @PastOrPresent(message = "A data deve ser o passado ou no presente")
    private LocalDate data_alvo;

    @NotBlank(message = "Nao deve ser nula")
    private double meta;


    private double valor_inicial;
    private  Long user_id;
}
