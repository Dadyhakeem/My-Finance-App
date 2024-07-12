package com.dev.hakeem.myfinanceapp.entity;

import com.dev.hakeem.myfinanceapp.enums.CategoriaReceitas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Objects;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_receita")
public class Receitas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name =  "data",nullable = false)
    private LocalDate data_receita;
    @Column(name = "descricao")
    private  String descricao;
    @Column(name = "valor",nullable = false,scale = 2)
    private double valor;
    @Column(name = "categoria")
    private CategoriaReceitas categoriaReceitas;
    @Enumerated(EnumType.STRING)
    @Column(name = "conta",nullable = false)
    private  Conta conta;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private  Conta conta_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receitas receitas = (Receitas) o;
        return Objects.equals(id, receitas.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
