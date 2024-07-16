package com.dev.hakeem.myfinanceapp.dto.cartaodto;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
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
public class UpdateCartaoDTO {


    private Long id;
    @FutureOrPresent(message = "A data deve ser no presente ou no futuro")
    private LocalDate vencimento;
    @FutureOrPresent(message = "A data deve ser no presente ou no futuro")
    private  LocalDate fechamento;
    @NotNull(message = "nao deve ser nulo")
    private  double limite;
    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

}
