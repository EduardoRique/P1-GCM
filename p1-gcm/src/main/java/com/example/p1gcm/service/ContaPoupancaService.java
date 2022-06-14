package com.example.p1gcm.service;

import com.example.p1gcm.model.ContaPoupanca;
import com.example.p1gcm.repository.ContaPoupancaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaPoupancaService implements ContaService{

    private final ContaPoupancaRepository contaPoupancaRepository;

    public boolean cadastrarConta(String id, double valorInicial) {
        if(valorInicial >= 0) {
            contaPoupancaRepository.save(new ContaPoupanca(id, new BigDecimal(valorInicial)));
            return true;
        }
        return false;
    }

    public BigDecimal consultarSaldo(String id) {
        Optional<ContaPoupanca> contaOpt = contaPoupancaRepository.findById(id);
        if(contaOpt.isPresent()) {
            return contaOpt.get().getSaldo();
        }
        return null;
    }

    public boolean credito(String id, double valor) {
        Optional<ContaPoupanca> contaOpt = contaPoupancaRepository.findById(id);
        if(contaOpt.isPresent()) {
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().add(new BigDecimal(valor)));
            contaPoupancaRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

    public boolean debito(String id, double valor) {
        Optional<ContaPoupanca> contaOpt = contaPoupancaRepository.findById(id);
        if(contaOpt.isPresent()) {
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().subtract(new BigDecimal(valor)));
            contaPoupancaRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

    public boolean transferir(String idContaOrigem, String idContaDestino, double valor) {
        Optional<ContaPoupanca> contaOptOrigem = contaPoupancaRepository.findById(idContaOrigem);
        Optional<ContaPoupanca> contaOptDestino = contaPoupancaRepository.findById(idContaDestino);
        if(contaOptOrigem.isPresent() && contaOptDestino.isPresent()) {
            if(contaOptOrigem.get().getSaldo().compareTo(new BigDecimal(valor)) != -1) {
                contaOptOrigem.get().setSaldo(contaOptOrigem.get().getSaldo().subtract(new BigDecimal(valor)));
                contaOptDestino.get().setSaldo(contaOptDestino.get().getSaldo().add(new BigDecimal(valor)));
                contaPoupancaRepository.save(contaOptOrigem.get());
                contaPoupancaRepository.save(contaOptDestino.get());
                return true;
            }
        }
        return false;
    }

    public boolean renderJuros(String id, double taxaJuros) {
        Optional<ContaPoupanca> contaOpt = contaPoupancaRepository.findById(id);
        if(contaOpt.isPresent()) {
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().add(contaOpt.get().getSaldo().multiply(new BigDecimal(taxaJuros))));
            contaPoupancaRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

}
