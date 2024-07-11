package com.dev.hakeem.myfinanceapp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transferenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "valor",nullable = false,scale = 2)
    private BigDecimal valor;

    @Column(name = "data")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "conta_origem")
    private Conta origem;

    @Enumerated(EnumType.STRING)
    @Column(name = "conta_destino")
    private  Conta destino;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private  Conta conta;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transferenca that = (Transferenca) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
