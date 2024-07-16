package com.dev.hakeem.myfinanceapp.repository;

import com.dev.hakeem.myfinanceapp.entity.Cartoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartoes,Long> {
    void delete(Cartoes cartao);
}
