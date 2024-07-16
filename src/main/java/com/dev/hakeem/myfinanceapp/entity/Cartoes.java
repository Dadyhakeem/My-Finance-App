package com.dev.hakeem.myfinanceapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_cartoes")
public class Cartoes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @Column(name = "credit_limit", nullable = false, scale = 2)
    private double limite;

    @Column(name = "fechamento", nullable = false)
    private LocalDate fechamento;

    @Column(name = "vencimento", nullable = false)
    private LocalDate vencimento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartoes cartoes = (Cartoes) o;
        return Objects.equals(id, cartoes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
