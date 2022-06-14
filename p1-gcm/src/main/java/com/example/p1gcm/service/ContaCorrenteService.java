package com.example.p1gcm.service;

import com.example.p1gcm.model.ContaCorrente;
import com.example.p1gcm.repository.ContaCorrenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaCorrenteService implements ContaService{

    private final ContaCorrenteRepository contaCorrenteRepository;

    public boolean cadastrarConta(String id, double saldoInicial) {
        contaCorrenteRepository.save(new ContaCorrente(id, new BigDecimal(saldoInicial)));
        return true;
    }

    public BigDecimal consultarSaldo(String id) {
        Optional<ContaCorrente> contaOpt = contaCorrenteRepository.findById(id);
        if(contaOpt.isPresent()) {
            return contaOpt.get().getSaldo();
        }
        return null;
    }

    public boolean credito(String id, double valor) {
        Optional<ContaCorrente> contaOpt = contaCorrenteRepository.findById(id);
        if(contaOpt.isPresent() && valor > 0) {
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().add(new BigDecimal(valor)));
            contaCorrenteRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

    public boolean debito(String id, double valor) {
        Optional<ContaCorrente> contaOpt = contaCorrenteRepository.findById(id);
        if(contaOpt.isPresent() && valor > 0 && contaOpt.get().getSaldo().subtract(new BigDecimal(valor)).compareTo(new BigDecimal(-1000)) > -1) {
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().subtract(new BigDecimal(valor)));
            contaCorrenteRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

    public boolean transferir(String idContaOrigem, String idContaDestino, double valor) {
        Optional<ContaCorrente> contaOptOrigem = contaCorrenteRepository.findById(idContaOrigem);
        Optional<ContaCorrente> contaOptDestino = contaCorrenteRepository.findById(idContaDestino);
        if(contaOptOrigem.isPresent() && contaOptDestino.isPresent() && valor > 0 && contaOptOrigem.get().getSaldo().subtract(new BigDecimal(valor)).compareTo(new BigDecimal(-1000)) > -1) {
            if(contaOptOrigem.get().getSaldo().compareTo(new BigDecimal(valor)) != -1) {
                contaOptOrigem.get().setSaldo(contaOptOrigem.get().getSaldo().subtract(new BigDecimal(valor)));
                contaOptDestino.get().setSaldo(contaOptDestino.get().getSaldo().add(new BigDecimal(valor)));
                contaCorrenteRepository.save(contaOptOrigem.get());
                contaCorrenteRepository.save(contaOptDestino.get());
                return true;
            }
        }
        return false;
    }
}
