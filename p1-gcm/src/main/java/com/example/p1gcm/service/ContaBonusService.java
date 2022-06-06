package com.example.p1gcm.service;

import com.example.p1gcm.model.ContaBonus;
import com.example.p1gcm.repository.ContaBonusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaBonusService implements ContaService{

    private final ContaBonusRepository contaBonusRepository;

    public boolean cadastrarConta(String id) {
        contaBonusRepository.save(new ContaBonus(id, new BigDecimal(0), 10));
        return true;
    }

    public BigDecimal consultarSaldo(String id) {
        Optional<ContaBonus> contaOpt = contaBonusRepository.findById(id);
        if(contaOpt.isPresent()) {
            return contaOpt.get().getSaldo();
        }
        return null;
    }

    public boolean credito(String id, double valor) {
        Optional<ContaBonus> contaOpt = contaBonusRepository.findById(id);
        if(contaOpt.isPresent()) {
            if(valor >= 100) {
                contaOpt.get().setPontuacao(contaOpt.get().getPontuacao()+1);
            }
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().add(new BigDecimal(valor)));
            contaBonusRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

    public boolean debito(String id, double valor) {
        Optional<ContaBonus> contaOpt = contaBonusRepository.findById(id);
        if(contaOpt.isPresent()) {
            contaOpt.get().setSaldo(contaOpt.get().getSaldo().subtract(new BigDecimal(valor)));
            contaBonusRepository.save(contaOpt.get());
            return true;
        }
        return false;
    }

    public boolean transferir(String idContaOrigem, String idContaDestino, double valor) {
        Optional<ContaBonus> contaOptOrigem = contaBonusRepository.findById(idContaOrigem);
        Optional<ContaBonus> contaOptDestino = contaBonusRepository.findById(idContaDestino);
        if(contaOptOrigem.isPresent() && contaOptDestino.isPresent()) {
            if(valor >= 200) {
                contaOptDestino.get().setPontuacao(contaOptDestino.get().getPontuacao()+1);
            }
            if(contaOptOrigem.get().getSaldo().compareTo(new BigDecimal(valor)) != -1) {
                contaOptOrigem.get().setSaldo(contaOptOrigem.get().getSaldo().subtract(new BigDecimal(valor)));
                contaOptDestino.get().setSaldo(contaOptDestino.get().getSaldo().add(new BigDecimal(valor)));
                contaBonusRepository.save(contaOptOrigem.get());
                contaBonusRepository.save(contaOptDestino.get());
                return true;
            }
        }
        return false;
    }


}
