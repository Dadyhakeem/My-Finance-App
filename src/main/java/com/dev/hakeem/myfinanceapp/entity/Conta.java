package com.dev.hakeem.myfinanceapp.entity;

import com.dev.hakeem.myfinanceapp.enums.TipoConta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;



@AllArgsConstructor
@NoArgsConstructor @Getter @Setter
@Entity
@Table(name = "tb_conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "instituicao_Financeira",nullable = false,length = 50)
    private  String instituicaoFinanceira;

    @Column(name = "saldo_inicial",nullable = true,scale = 2)
    private double saldoInicial;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta",nullable = false)
    private TipoConta tipoconta;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
