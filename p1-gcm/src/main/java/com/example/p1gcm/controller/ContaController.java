package com.example.p1gcm.controller;

import com.example.p1gcm.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.math.BigDecimal;

@RestController
@Api(value = "Conta")
@RequiredArgsConstructor
@Service
public class ContaController {

    @Inject
    ContaService contaService;

    @ApiOperation(value = "Cria uma nova conta com saldo inicial")
    @PostMapping("/cadastraConta")
    public ResponseEntity<Boolean> cadastrarConta(String id, double saldoInicial) {
        return ResponseEntity.ok(contaService.cadastrarConta(id, saldoInicial));
    }

    @ApiOperation(value = "Retorna o saldo de uma conta")
    @GetMapping("/consultaSaldo")
    public ResponseEntity<BigDecimal> consultarSaldo(String id) {
        return ResponseEntity.ok(contaService.consultarSaldo(id));
    }

    @ApiOperation(value = "Credita o valor em uma conta")
    @PutMapping("/credito")
    public ResponseEntity<Boolean> credito(String id, double valor) {
        return ResponseEntity.ok(contaService.credito(id, valor));
    }

    @ApiOperation(value = "Debita o valor de uma conta")
    @PutMapping("/debito")
    public ResponseEntity<Boolean> debito(String id, double valor) {
        return ResponseEntity.ok(contaService.debito(id, valor));
    }

    @ApiOperation(value = "Transfere o saldo de uma conta para outra")
    @PutMapping("/transferencia")
    public ResponseEntity<Boolean> transferir(String idContaOrigem, String idContaDestino, double valor) {
        return ResponseEntity.ok(contaService.transferir(idContaOrigem, idContaDestino, valor));
    }

}
