package com.vinnom.alura.leilaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinnom.alura.leilaoapi.model.Leilao;

public interface LeilaoRepository extends JpaRepository<Leilao, Long> {

}
