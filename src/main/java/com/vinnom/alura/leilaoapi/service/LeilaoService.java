package com.vinnom.alura.leilaoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.sun.istack.NotNull;
import com.vinnom.alura.leilaoapi.model.Lance;
import com.vinnom.alura.leilaoapi.model.Leilao;
import com.vinnom.alura.leilaoapi.repository.LeilaoRepository;

@Service
public class LeilaoService {

	@Autowired
	private LeilaoRepository repository;

	@NonNull
	public List<Leilao> todos() {
		return repository.findAll();
	}

	@NonNull
	public Optional<Leilao> buscaPorId(long id) {
		return repository.findById(id);
	}

	public void salvaNovoLance(@NotNull Lance lance, @NotNull Leilao leilao) {
		leilao.valida(lance);
		leilao.propoe(lance);
		repository.save(leilao);
	}

	@NonNull
	public Leilao salva(@NotNull Leilao leilao) {
		return repository.save(leilao);
	}

}
