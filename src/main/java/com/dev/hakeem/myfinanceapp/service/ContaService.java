package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.ContaRequestDTO;
import com.dev.hakeem.myfinanceapp.dto.TransferenciaDTO;
import com.dev.hakeem.myfinanceapp.dto.contadto.DepositoRequestDTO;
import com.dev.hakeem.myfinanceapp.dto.contadto.SaqueRequestDTO;
import com.dev.hakeem.myfinanceapp.dto.contadto.TransferenciaRequestDTO;
import com.dev.hakeem.myfinanceapp.entity.Conta;
import com.dev.hakeem.myfinanceapp.entity.User;
import com.dev.hakeem.myfinanceapp.repository.ContaRepository;
import com.dev.hakeem.myfinanceapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository repository;
    private final TransferencaService transferencaService;
    private  final UserRepository userRepository;

    @Autowired
    public ContaService(ContaRepository repository, TransferencaService transferencaService, UserRepository userRepository) {
        this.repository = repository;
        this.transferencaService = transferencaService;
        this.userRepository = userRepository;
    }

    /**
     * Cria uma nova conta com base nos dados fornecidos no DTO.
     *
     * @param requestDTO Dados da conta a serem criada
     * @return Conta criada
     * @throws IllegalArgumentException Se os dados fornecidos forem inválidos
     */
    public Conta createConta(@Valid ContaRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Conta novaConta = new Conta();
        novaConta.setTipoconta(requestDTO.getTipoConta()); // Converte TipoConta para String se necessário
        novaConta.setInstituicaoFinanceira(requestDTO.getInstituicaoFinanceira());
        novaConta.setSaldoInicial(requestDTO.getSaldoInicial());
        novaConta.setUser(user);

        return repository.save(novaConta);
    }

    /**
     * Realiza um depósito em uma conta.
     *
     * @param requestDTO Os dados do depósito, incluindo o ID da conta e o valor a ser depositado.
     * @return A conta atualizada após o depósito.
     */
    public Conta depositar(DepositoRequestDTO requestDTO) {
        // Busca a conta pelo ID
        Conta conta = repository.findById(requestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        // Realiza o depósito
        conta.setSaldoInicial(conta.getSaldoInicial() + requestDTO.getValor());

        // Salva a conta atualizada no banco de dados
        return repository.save(conta);
    }

    /**
     * Saca um valor da conta especificada e atualiza no banco de dados.
     *
     *  Conta da qual o saque será realizado
     *  Valor a ser sacado
     * @throws IllegalArgumentException Se o valor do saque for negativo ou maior que o saldo disponível
     */
    public void sacar(SaqueRequestDTO requestDTO) {
        // Busca a conta pelo ID
        Conta conta = repository.findById(requestDTO.getContaId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        // Validação do valor do saque
        if (requestDTO.getValor() <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser maior que zero");
        }
        if (requestDTO.getValor() > conta.getSaldoInicial()) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque");
        }

        // Realiza o saque
        double novoSaldo = conta.getSaldoInicial() - requestDTO.getValor();
        conta.setSaldoInicial(novoSaldo);

        // Atualiza a conta no banco de dados usando o repositório
        repository.save(conta);
    }


    public Conta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }


    /**
     * Atualiza uma conta existente com base nos dados fornecidos no DTO.
     *
     * @param id ID da conta a ser atualizada
     * @param requestDTO Dados atualizados da conta
     * @return Conta atualizada
     * @throws IllegalArgumentException Se os dados fornecidos forem inválidos
     */
    public Conta atualizarConta(Long id, @Valid ContaRequestDTO requestDTO) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (requestDTO.getTipoConta() != null) {
            conta.setTipoconta(requestDTO.getTipoConta());
        }
        if (requestDTO.getInstituicaoFinanceira() != null && !requestDTO.getInstituicaoFinanceira().isEmpty()) {
            conta.setInstituicaoFinanceira(requestDTO.getInstituicaoFinanceira());
        }
        if (requestDTO.getSaldoInicial() >= 0) {
            conta.setSaldoInicial(requestDTO.getSaldoInicial());
        }

        return repository.save(conta);
    }

    /**
     * Lista todas as contas no sistema.
     *
     * @return Lista de contas
     */
    public List<Conta> listarContas() {
        return repository.findAll();
    }

    /**
     * Exclui uma conta pelo seu ID.
     *
     * @param id ID da conta a ser excluída
     */
    public void excluirConta(Long id) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        repository.delete(conta);
    }

    /**
     * Transfere fundos de uma conta para outra.
     *
     *  Conta de origem
     *  Conta de destino
     *  Valor a ser transferido
     * @throws IllegalArgumentException Se o valor da transferência for inválido ou saldo insuficiente
     */
    public void transferir(TransferenciaRequestDTO requestDTO) {
        Conta contaOrigem = repository.findById(requestDTO.getContaOrigemId())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));

        Conta contaDestino = repository.findById(requestDTO.getContaDestinoId())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        if (requestDTO.getValor() <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser maior que zero");
        }
        if (requestDTO.getValor() > contaOrigem.getSaldoInicial()) {
            throw new IllegalArgumentException("Saldo insuficiente na conta de origem para realizar a transferência");
        }

        contaOrigem.setSaldoInicial(contaOrigem.getSaldoInicial() - requestDTO.getValor());
        contaDestino.setSaldoInicial(contaDestino.getSaldoInicial() + requestDTO.getValor());

        repository.save(contaOrigem);
        repository.save(contaDestino);
    }

}
