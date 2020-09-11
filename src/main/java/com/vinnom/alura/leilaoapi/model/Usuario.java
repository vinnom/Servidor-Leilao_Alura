package com.vinnom.alura.leilaoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final long id;
	
	@NonNull
	private final String nome;
	
	public Usuario() {
		this.id = 0L;
		this.nome = "";
	}

	public Usuario(String nome) {
		this.id = 0L;
		this.nome = nome;
	}

	public Usuario(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Usuario usuario = (Usuario) o;

		if(id != usuario.id) return false;
		return nome.equals(usuario.nome);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + nome.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "(" + id + ") " + nome;
	}

	public String getNome() {
		return nome;
	}
}
