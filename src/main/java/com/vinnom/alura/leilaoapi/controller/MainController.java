package com.vinnom.alura.leilaoapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.istack.NotNull;
import com.vinnom.alura.leilaoapi.model.Leilao;
import com.vinnom.alura.leilaoapi.service.LeilaoService;

@Controller
@RequestMapping(value = "/")
public class MainController {

	private LeilaoService leilaoService;

	public MainController(@NotNull LeilaoService leilaoService) {
		this.leilaoService = leilaoService;
	}

	@ModelAttribute("leiloes")
	@NotNull
	public List<String> leiloes() {
		List<Leilao> todos = leilaoService.todos();
		List<String> apresentaveis = new ArrayList<>();
		todos.forEach(leilao -> {
			String formatada = leilao.getId() + " - " + leilao.getDescricao();
			apresentaveis.add(formatada);
		});
		
		return apresentaveis;
	}

	@GetMapping
	@NotNull
	public String index() {
		return "index";
	}
}
