package com.vinnom.alura.leilaoapi.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetService{
   
   @Autowired
   private EntityManager em;
   
   @Transactional
   public void limpaBD() {
      String limpaTodasTabelas = "TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK";
      Query query = em.createNativeQuery(limpaTodasTabelas);
      query.executeUpdate();
   }
}
