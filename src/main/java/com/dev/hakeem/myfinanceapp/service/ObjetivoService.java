package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.ObjetivosDTO;
import com.dev.hakeem.myfinanceapp.entity.Objetivos;
import com.dev.hakeem.myfinanceapp.repository.ObjetivoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoService {
    @Autowired
    private  final ObjetivoRepository repository;

    public ObjetivoService(ObjetivoRepository repository) {
        this.repository = repository;
    }

    public Objetivos createObjetivo(@Valid ObjetivosDTO objetivosDTO){
        Objetivos objetivos = new Objetivos();

        objetivos.setDescricao(objetivosDTO.getDescricao());
        objetivos.setData_alvo(objetivosDTO.getData_alvo());
        objetivos.setMeta(objetivosDTO.getMeta());
        objetivos.setValor_inicial(objetivosDTO.getValor_inicial());

       return repository.save(objetivos);

    }

    public  void excluirObjetivo(Long id){
        Objetivos objetivos = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objetivo nao encontrado"));

         repository.deleteById(id);
    }

    public  Objetivos atualizar (@Valid ObjetivosDTO objetivosDTO){
        Objetivos objetivos = repository.findById(objetivosDTO.getId())
                .orElseThrow(() -> new RuntimeException("Objetivo nao encontrado"));
        objetivos.setDescricao(objetivosDTO.getDescricao());
        objetivos.setData_alvo(objetivosDTO.getData_alvo());
        objetivos.setMeta(objetivosDTO.getMeta());
        objetivos.setValor_inicial(objetivosDTO.getValor_inicial());
        return repository.save(objetivos);
    }
}
