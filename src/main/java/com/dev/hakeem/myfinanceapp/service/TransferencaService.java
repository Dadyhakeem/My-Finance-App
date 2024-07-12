package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.TransferenciaDTO;
import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.repository.ContaRepository;
import com.dev.hakeem.myfinanceapp.repository.TranferencaRepository;

import java.util.Optional;

public class TransferencaService {

    private  final TranferencaRepository repository;
    private final ContaRepository contaRepository;

    public TransferencaService(TranferencaRepository repository, ContaRepository contaRepository) {
        this.repository = repository;
        this.contaRepository = contaRepository;
    }

    /**
     * Realiza a transferência entre contas.
     *
     * @param transferenciaDTO Objeto contendo as informações da transferência.
     * @throws IllegalArgumentException se as contas de origem ou destino forem nulas ou o valor da transferência for não positivo.
     */

    public  void transferencia(TransferenciaDTO transferenciaDTO){
        // Verifica se a origem e destino são válidos
        if (transferenciaDTO.getOrigem() == null || transferenciaDTO.getDestino() == null){
            throw  new IllegalArgumentException("Conta origem e destino devem ser especificadas.");
        }

        // Verifica se o valor da transferência é positivo
        if (transferenciaDTO.getValor() <= 0){
            throw  new IllegalArgumentException("O valor da transferência deve ser positivo.");

        }

        // Busca as contas de origem e destino no banco de dados
        Optional<Conta> contaOrigem1 = contaRepository.findById(transferenciaDTO.getOrigem().getId());
        Optional<Conta> contaDestino1 = contaRepository.findById(transferenciaDTO.getDestino().getId());

        // Verifica se as contas existem
        if (!contaOrigem1.isPresent() || !contaDestino1.isPresent()){
            throw new IllegalArgumentException("Conta origem ou destino não encontrada.");
        }

        Conta contaOrigem = contaOrigem1.get();
        Conta contaDestino = contaDestino1.get();

        // Realiza a lógica de débito e crédito nas contas
        debitar(contaOrigem,transferenciaDTO.getValor());
        creditar(contaDestino,transferenciaDTO.getValor());

        // Salva as alterações nas contas
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);


        // Simulação da transferência com sucesso
        System.out.println("Transferência realizada com sucesso! Transferido " + transferenciaDTO.getValor() +
                " de " + contaOrigem.getUser().getName() + " para " + contaDestino.getUser().getName() +
                " na data " + transferenciaDTO.getData());


    }


    /**
     * Debita um valor da conta de origem.
     *
     * @param origem A conta de onde o valor será debitado.
     * @param valor O valor a ser debitado.
     * @throws IllegalArgumentException se o saldo da conta de origem for insuficiente.
     */

    public void debitar(Conta origem,double valor){
        // Verifica se a conta de origem tem saldo suficiente
        if (origem.getSaldoInicial()< valor){
            throw  new IllegalArgumentException("Saldo insuficiente na conta de origem.");
        }

        // Debita o valor da conta de origem
        origem.setSaldoInicial(origem.getSaldoInicial()- valor);
    }

    /**
     * Credita um valor na conta de destino.
     *
     * @param destino A conta onde o valor será creditado.
     * @param valor O valor a ser creditado.
     */

    public void creditar(Conta destino, double valor){
        // Credita o valor na conta de destino
        destino.setSaldoInicial(destino.getSaldoInicial() + valor);
    }




}
