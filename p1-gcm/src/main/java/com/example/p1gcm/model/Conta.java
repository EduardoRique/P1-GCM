package com.example.p1gcm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class Conta {

    private String id;

    private BigDecimal saldo;
}
