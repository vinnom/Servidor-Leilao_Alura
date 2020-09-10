package com.vinnom.alura.leilaoapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.vinnom.alura.leilaoapi.exception.QuantidadeMaximaDeLancesException;
import com.vinnom.alura.leilaoapi.exception.UsuarioDeuLancesSeguidosException;
import com.vinnom.alura.leilaoapi.exception.ValorMenorQueOAnteriorException;

@Entity
public class Leilao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final long id;
	private final String descricao;
	
	@OneToMany(mappedBy = "leilao", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private final List<Lance> lances;

	public Leilao(String descricao) {
		this.id = 0L;
		this.descricao = descricao;
		this.lances = new ArrayList<>();
		this.lances.add(new Lance(new Usuario(""), 0.0));
	}

	public void propoe(Lance lance) {
		if(lances.get(0).getValor() == 0.0) {
			lances.set(0, lance);
		} else {
			valida(lance);
			lances.add(0, lance);
		}
	}

	private void valida(Lance lance) {
		validaLanceSemRepeticaoDoUsuario(lance);
		validaQuantidadeMaximaDeLancesDeUmMesmoUsuario(lance);
		validaValorMaiorQueAnterior(lance);
	}

	private void validaLanceSemRepeticaoDoUsuario(Lance lance) {
		if(lance.getUsuario().equals(lances.get(0).getUsuario())) {
			throw new UsuarioDeuLancesSeguidosException();
		}
	}

	private void validaQuantidadeMaximaDeLancesDeUmMesmoUsuario(Lance lance) {
		int contador = 0;
		for(Lance l : lances) {
			if(lance.getUsuario().equals(l.getUsuario())) {
				contador++;
			}
		}
		if(contador == 5) {
			throw new QuantidadeMaximaDeLancesException();
		}
	}

	private void validaValorMaiorQueAnterior(Lance lance) {
		if(lance.getValor() < lances.get(0).getValor())
			throw new ValorMenorQueOAnteriorException();
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public Lance getMaiorLance() {
//		if(lances.isEmpty()) {
//			return null;
//		}
		return lances.get(0);
	}

	public String getMaiorLanceFormatado() {
		return getMaiorLance().getValorFormatado();
	}

	public Lance getMenorLance() {
//		if(lances.isEmpty()) {
//			return null;
//		}
		return lances.get(lances.size() - 1);
	}

	public String getMenorLanceFormatado() {
		return getMenorLance().getValorFormatado();
	}

	public List<Lance> getTresMaioresLances() {
		if(lances.isEmpty()) {
			return lances;
		}
		if(lances.size() < 3) {
			return lances.subList(0, lances.size());
		}
		return lances.subList(0, 3);
	}

	public long getId() {
		return id;
	}
}
