package com.dev.hakeem.myfinanceapp.entity;

import com.dev.hakeem.myfinanceapp.enums.CategoriaDespesas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_despesas")
public class Despesas implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "descricao")
    private  String descriao;

    @Column(name = "valor",nullable = false,scale = 2)
    private double valor;


    @Column(name = "conta")
    private  Conta conta;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorias")
    private CategoriaDespesas categoriaDespesas;

    @Column(name = "data",nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta_id;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Despesas despesas = (Despesas) o;
        return Objects.equals(id, despesas.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
