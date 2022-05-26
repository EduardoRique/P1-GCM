package com.example.p1gcm.service;

import com.example.p1gcm.model.Conta;
import com.example.p1gcm.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public boolean cadastrarConta(String id) {
        contaRepository.save(new Conta(id, new BigDecimal(0)));
        return true;
    }

    public BigDecimal consultarSaldo(String id) {
        Optional<Conta> contaOpt = contaRepository.findById(id);
        if(contaOpt.isPresent()) {
            return contaOpt.get().getSaldo();
        }
        return null;
    }

    public boolean credito(String id, double valor) {
        Optional<Conta> contaOpt = contaRepository.findById(id);
        if(contaOpt.isPresent()) {
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().add(new BigDecimal(valor)));
            contaRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

    public boolean debito(String id, double valor) {
        Optional<Conta> contaOpt = contaRepository.findById(id);
        if(contaOpt.isPresent()) {
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().subtract(new BigDecimal(valor)));
            contaRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

    public boolean transferir(String idContaOrigem, String idContaDestino, double valor) {
        Optional<Conta> contaOptOrigem = contaRepository.findById(idContaOrigem);
        Optional<Conta> contaOptDestino = contaRepository.findById(idContaDestino);
        if(contaOptOrigem.isPresent() && contaOptDestino.isPresent()) {
            this.debito(contaOptOrigem.get().getId(), valor);
            this.credito(contaOptDestino.get().getId(), valor);
            contaRepository.save(contaOptOrigem.get());
            contaRepository.save(contaOptDestino.get());
            return true;
        }
        return false;
    }


}
