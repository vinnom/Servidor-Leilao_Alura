package com.vinnom.alura.leilaoapi.model;

import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Lance {

	private static final Locale BRASIL = new Locale("pt", "BR");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final long id;
	
	@ManyToOne
	@Cascade(CascadeType.ALL)
	@NonNull
	private final Usuario usuario;
	private final double valor;
	private final String valorFormatado;
	
	public Lance() {
		this.id = 0L;
		this.usuario = null;
		this.valor = 0.0;
		this.valorFormatado = formata(valor);
	}
	
	public Lance(
			@NonNull Usuario usuario,
			double valor) {
		this.id = 0L;
		this.usuario = usuario;
		this.valor = valor;
		this.valorFormatado = formata(valor);
	}

	public Lance(
			@JsonProperty(value = "id", access = Access.READ_ONLY) Long id,
			@NonNull Usuario usuario,
			double valor) {
		this.id = id;
		this.usuario = usuario;
		this.valor = valor;
		this.valorFormatado = formata(valor);
	}

	public double getValor() {
		return valor;
	}

	public String getValorFormatado() {
		if(valorFormatado !=  null) {
			return valorFormatado;
		}
		return "";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = usuario.hashCode();
		temp = Double.doubleToLongBits(valor);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Lance lance = (Lance) o;

		if(Double.compare(lance.valor, valor) != 0) return false;
		return usuario.equals(lance.usuario);
	}

	public String formata(double valor) {
		NumberFormat formatador = NumberFormat.getCurrencyInstance(Lance.BRASIL);
		return formatador.format(valor);
	}
}
