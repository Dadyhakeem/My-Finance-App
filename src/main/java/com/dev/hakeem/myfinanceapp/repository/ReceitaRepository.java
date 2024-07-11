package com.dev.hakeem.myfinanceapp.repository;

import com.dev.hakeem.myfinanceapp.entity.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receitas,Long> {
}
