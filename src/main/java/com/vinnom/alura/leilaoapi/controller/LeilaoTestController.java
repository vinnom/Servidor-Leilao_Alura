package com.vinnom.alura.leilaoapi.controller;

import static com.vinnom.alura.leilaoapi.controller.constante.LeilaoControllerConstantes.TESTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinnom.alura.leilaoapi.model.Leilao;
import com.vinnom.alura.leilaoapi.service.LeilaoService;

@Controller
@RequestMapping("leilao")
@Profile(TESTE)
public class LeilaoTestController{

   @Autowired
   private LeilaoService service;

   @PostMapping
   @Profile(TESTE)
   public ResponseEntity<Leilao> novo(@RequestBody String descricao){
      Leilao leilaoSalvo = service.salva(new Leilao(descricao));
      return ResponseEntity.ok(leilaoSalvo);
   }
}
