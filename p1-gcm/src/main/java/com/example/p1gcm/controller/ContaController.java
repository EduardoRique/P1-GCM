package com.example.p1gcm.controller;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface ContaController {

    public ResponseEntity<Boolean> cadastrarConta(String id);

    public ResponseEntity<BigDecimal> consultarSaldo(String id);

    public ResponseEntity<Boolean> credito(String id, double valor);

    public ResponseEntity<Boolean> debito(String id, double valor);

    public ResponseEntity<Boolean> transferir(String idContaOrigem, String idContaDestino, double valor);
}
