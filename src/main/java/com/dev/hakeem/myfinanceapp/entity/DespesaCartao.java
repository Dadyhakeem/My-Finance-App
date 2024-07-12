package com.dev.hakeem.myfinanceapp.entity;

import com.dev.hakeem.myfinanceapp.enums.CategoriaDespesas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "despesa_cartoes")
public class DespesaCartao implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id ;

    @Column(name = "data",nullable = false)
    private LocalDate data;

    @Column(name = "descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria",nullable = false)
    private CategoriaDespesas categoriaDespesas;


    @Column(name = "cartao",nullable = false)
    private  Cartoes cartoes;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartoes cartoes_id;

    @ManyToOne
    @JoinColumn(name = "despesa_id")
    private  Despesas despesas;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DespesaCartao that = (DespesaCartao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
