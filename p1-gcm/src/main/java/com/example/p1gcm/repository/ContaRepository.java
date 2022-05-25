package com.example.p1gcm.repository;

import com.example.p1gcm.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, String> {
}
