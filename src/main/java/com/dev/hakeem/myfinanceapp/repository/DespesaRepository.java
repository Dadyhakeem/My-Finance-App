package com.dev.hakeem.myfinanceapp.repository;

import com.dev.hakeem.myfinanceapp.entity.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesas,Long> {
}
