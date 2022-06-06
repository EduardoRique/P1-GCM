package com.example.p1gcm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "ContaBonus")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContaBonus {

    @Id
    private String id;

    @Column
    private BigDecimal saldo;

    @Column
    private Integer pontuacao;
}
