package com.example.p1gcm.controller;

import com.example.p1gcm.service.ContaPoupancaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.math.BigDecimal;

@RestController
@Api(value = "ContaPoupanca")
@RequiredArgsConstructor
@Service
@RequestMapping("/contaPoupanca")
public class ContaPoupancaController implements ContaController {

    @Inject
    ContaPoupancaService contaPoupancaService;

    @ApiOperation(value = "Cria uma nova conta corrente com saldo 0 (zero)")
    @PostMapping("/cadastraConta")
    public ResponseEntity<Boolean> cadastrarConta(String id) {
        return ResponseEntity.ok(contaPoupancaService.cadastrarConta(id, 0));
    }

    @ApiOperation(value = "Retorna o saldo de uma conta")
    @GetMapping("/consultaSaldo")
    public ResponseEntity<BigDecimal> consultarSaldo(String id) {
        return ResponseEntity.ok(contaPoupancaService.consultarSaldo(id));
    }

    @ApiOperation(value = "Credita o valor em uma conta")
    @PutMapping("/credito")
    public ResponseEntity<Boolean> credito(String id, double valor) {
        return ResponseEntity.ok(contaPoupancaService.credito(id, valor));
    }

    @ApiOperation(value = "Debita o valor de uma conta")
    @PutMapping("/debito")
    public ResponseEntity<Boolean> debito(String id, double valor) {
        return ResponseEntity.ok(contaPoupancaService.debito(id, valor));
    }

    @ApiOperation(value = "Transfere o saldo de uma conta para outra")
    @PutMapping("/transferencia")
    public ResponseEntity<Boolean> transferir(String idContaOrigem, String idContaDestino, double valor) {
        return ResponseEntity.ok(contaPoupancaService.transferir(idContaOrigem, idContaDestino, valor));
    }

    @ApiOperation(value = "Rende o juros da conta")
    @PutMapping("/rendeJuros")
    public ResponseEntity<Boolean> renderJuros(String id, double taxaJuros) {
        return ResponseEntity.ok(contaPoupancaService.renderJuros(id, taxaJuros));
    }

}
