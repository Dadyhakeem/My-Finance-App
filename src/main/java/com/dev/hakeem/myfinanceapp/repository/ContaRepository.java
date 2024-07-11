package com.dev.hakeem.myfinanceapp.repository;

import com.dev.hakeem.myfinanceapp.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta,Long> {
}
