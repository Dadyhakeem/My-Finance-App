package com.dev.hakeem.myfinanceapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_objetivos")
public class Objetivos implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;
    @Column(name =  "descricao")
    private String descricao;
    @Column(name = "data_alvo",nullable = false,length = 150)
    private LocalDate data_alvo;
    @Column(name = "meta", nullable = false,scale = 2)
    private double meta;
    @Column(name = "valor_inicial",scale = 2)
    private  double valor_inicial;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objetivos objetivos = (Objetivos) o;
        return Objects.equals(id, objetivos.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
