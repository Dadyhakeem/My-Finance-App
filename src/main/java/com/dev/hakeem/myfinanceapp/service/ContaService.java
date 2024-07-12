package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.ContaRequestDTO;
import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.repository.ContaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ContaService {
    @Autowired
    private final ContaRepository repository;


     // Injeção de Dependência
    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }


    /**
     * Cria uma nova conta com base nos dados fornecidos no DTO.
     *
     * @param requestDTO Dados da conta a serem criada
     * @return Conta criada
     * @throws IllegalArgumentException Se os dados fornecidos forem inválidos
     */

    public  Conta createConta(@Valid ContaRequestDTO requestDTO){
        // validaçao dos dados do DTO
        if (requestDTO.getTipoConta() == null){
            throw new IllegalArgumentException("Tipo de conta não pode ser nulo");
        }
        if (requestDTO.getInstituicaoFinanceira() == null || requestDTO.getInstituicaoFinanceira().isEmpty()) {
            throw new IllegalArgumentException("Instituição financeira não pode ser nula ou vazia ");
        }
            if (requestDTO.getSaldoInicial()< 0){
                throw new IllegalArgumentException("Saldo inicial não pode ser negativo");
            }
            // criacao da conta

          Conta newConta = new  Conta();

            newConta.setTipoconta(requestDTO.getTipoConta());
            newConta.setInstituicaoFinanceira(requestDTO.getInstituicaoFinanceira());
            newConta.setSaldoInicial(requestDTO.getSaldoInicial());


            // Salva a conta no banco de dados usando o repositório
               newConta = repository.save(newConta);

               return  newConta;


    }

    /**
     * Deposita um valor na conta especificada.
     *
     * @param conta Conta na qual o depósito será realizado
     * @param valor Valor a ser depositado
     * @throws IllegalArgumentException Se o valor do depósito for negativo
     */


           public  void depositar(Conta conta, double valor){
               // validação do valor do deposito
               if (valor <= 0){
                   throw new IllegalArgumentException("Valor do depósito deve ser maior que zero");
               }
               // Realize o deposito  na conta

               double novoSaldo = conta.getSaldoInicial()+valor;
               conta.setSaldoInicial(novoSaldo);

               // Atualiza a conta no banco de dados usando o repositório
                   repository.save(conta);
           }


    /**
     * Deposita um valor na conta especificada e atualiza no banco de dados.
     * Saca um valor da conta especificada.
     *
     * @param conta Conta da qual o saque será realizado
     * @param valor Valor a ser sacado
     * @throws IllegalArgumentException Se o valor do saque for negativo ou maior que o saldo disponível
     */

           public  void sacar (Conta conta,double valor){
               // validação do valor do saque
               if (valor <= 0){
                   throw new IllegalArgumentException("Valor do saque deve ser maior que zero ");
               }

               // Realiza o saque
               double novosaldo = conta.getSaldoInicial() - valor;
               conta.setSaldoInicial(novosaldo);

               // Atualiza a conta no banco de dados usando o repositório
                 repository.save(conta);
           }


}
