package com.dev.hakeem.myfinanceapp.entity;

import com.dev.hakeem.myfinanceapp.enums.CategoriaReceitas;
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
@Table(name = "tb_receita")
public class Receitas implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name =  "data",nullable = false)
    private LocalDate data_receita;
    @Column(name = "descricao")
    private  String descricao;
    @Column(name = "valor",nullable = false,scale = 2)
    private double valor;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaReceitas categoriaReceitas;
    @ManyToOne
    @JoinColumn(name = "conta_id",nullable = false)
    private  Conta conta;


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
