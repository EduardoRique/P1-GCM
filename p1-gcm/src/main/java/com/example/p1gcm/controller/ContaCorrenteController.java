package com.example.p1gcm.controller;

import com.example.p1gcm.service.ContaCorrenteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.math.BigDecimal;

@RestController
@Api(value = "ContaCorrente")
@RequiredArgsConstructor
@Service
@RequestMapping("/contaCorrente")
public class ContaCorrenteController implements  ContaController{

    @Inject
    ContaCorrenteService contaCorrenteService;

    @ApiOperation(value = "Cria uma nova conta corrente com saldo 0 (zero)")
    @PostMapping("/cadastraConta")
    public ResponseEntity<Boolean> cadastrarConta(String id) {
        return ResponseEntity.ok(contaCorrenteService.cadastrarConta(id, 0));
    }

    @ApiOperation(value = "Retorna o saldo de uma conta")
    @GetMapping("/consultaSaldo")
    public ResponseEntity<BigDecimal> consultarSaldo(String id) {
        return ResponseEntity.ok(contaCorrenteService.consultarSaldo(id));
    }

    @ApiOperation(value = "Credita o valor em uma conta")
    @PutMapping("/credito")
    public ResponseEntity<Boolean> credito(String id, double valor) {
        return ResponseEntity.ok(contaCorrenteService.credito(id, valor));
    }

    @ApiOperation(value = "Debita o valor de uma conta")
    @PutMapping("/debito")
    public ResponseEntity<Boolean> debito(String id, double valor) {
        return ResponseEntity.ok(contaCorrenteService.debito(id, valor));
    }

    @ApiOperation(value = "Transfere o saldo de uma conta para outra")
    @PutMapping("/transferencia")
    public ResponseEntity<Boolean> transferir(String idContaOrigem, String idContaDestino, double valor) {
        return ResponseEntity.ok(contaCorrenteService.transferir(idContaOrigem, idContaDestino, valor));
    }

}
