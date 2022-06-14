package com.example.p1gcm.service;

import java.math.BigDecimal;

public interface ContaService {

    public boolean cadastrarConta(String id, double saldoInicial);

    public BigDecimal consultarSaldo(String id);

    public boolean credito(String id, double valor);

    public boolean debito(String id, double valor);

    public boolean transferir(String idContaOrigem, String idContaDestino, double valor);
}
