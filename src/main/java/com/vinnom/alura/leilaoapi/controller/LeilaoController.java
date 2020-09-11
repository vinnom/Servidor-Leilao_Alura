package com.vinnom.alura.leilaoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sun.istack.NotNull;
import com.vinnom.alura.leilaoapi.exception.QuantidadeMaximaDeLancesException;
import com.vinnom.alura.leilaoapi.exception.UsuarioDeuLancesSeguidosException;
import com.vinnom.alura.leilaoapi.exception.ValorMenorQueOAnteriorException;
import com.vinnom.alura.leilaoapi.model.Lance;
import com.vinnom.alura.leilaoapi.model.Leilao;
import com.vinnom.alura.leilaoapi.service.LeilaoService;

@RestController
@RequestMapping(value = "leilao")
public class LeilaoController {

	@Autowired
	private LeilaoService service;

	@PutMapping({"{id}/lance"})
	@NotNull
	public ResponseEntity<Object> propoeLance(@PathVariable("id") long id, @RequestBody @NotNull Lance lance) {
		ResponseEntity<Object> responseEntity;
		try {
			Leilao leilao = service.buscaPorId(id).get();
			service.salvaNovoLance(lance, leilao);
			responseEntity = ResponseEntity.ok(leilao);
		} catch (Exception e) {
			Exception exception1 = e;
			String mensagemDeErro = (exception1 instanceof java.util.NoSuchElementException
					|| exception1 instanceof ValorMenorQueOAnteriorException
					|| exception1 instanceof UsuarioDeuLancesSeguidosException
					|| exception1 instanceof QuantidadeMaximaDeLancesException) ? e.getMessage()
							: "Não foi possível propor o lance";
			responseEntity = ResponseEntity.badRequest().body(mensagemDeErro);
		}
		return responseEntity;
	}

	@GetMapping
	@NotNull
	public ResponseEntity<List<Leilao>> todos() {
		List<Leilao> todosLeiloes = service.todos();
		return ResponseEntity.ok(todosLeiloes);
	}

	@PostMapping("/form")
	@NotNull
	public ModelAndView novoLeilao(@NotNull String descricao) {
		Leilao leilao = new Leilao(descricao);
		ResponseEntity.ok(service.salva(leilao));
		return new ModelAndView("redirect:/");
	}
}
