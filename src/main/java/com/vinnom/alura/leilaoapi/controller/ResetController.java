package com.vinnom.alura.leilaoapi.controller;

import static com.vinnom.alura.leilaoapi.controller.constante.LeilaoControllerConstantes.TESTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinnom.alura.leilaoapi.service.ResetService;

@Controller
@RequestMapping("reset")
@Profile(TESTE)
public class ResetController{

   private final String BANCO_LIMPADO_COM_SUCESSO = "Banco de dados limpado";

   @Autowired
   private ResetService service;

   @GetMapping
   public ResponseEntity<String> reset(){
      service.limpaBD();
      return ResponseEntity.ok(BANCO_LIMPADO_COM_SUCESSO);
   }
}
