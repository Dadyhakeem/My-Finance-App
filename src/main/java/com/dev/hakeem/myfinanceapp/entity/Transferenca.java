package com.dev.hakeem.myfinanceapp.entity;

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
@Table(name = "tb_transferencias")
public class Transferenca implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "valor",nullable = false,scale = 2)
    private double  valor;

    @Column(name = "data")
    private LocalDate data;


    @Column(name = "conta_origem")
    private Conta origem;


    @Column(name = "conta_destino")
    private  Conta destino;

    @ManyToOne
    @JoinColumn(name = "conta_id",referencedColumnName = "id")
    private Conta contaId;


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
