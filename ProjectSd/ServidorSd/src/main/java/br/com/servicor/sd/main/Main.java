package br.com.servicor.sd.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.service.JogadorService;

public class Main {

	
	
	
	public static void main(String[] args) throws java.text.ParseException {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ServidorSd");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Jogador jogador = new Jogador();
		jogador.setNome("iury");
		jogador.setNivel("Alto");
		jogador.setHost("192.168.0.100");
		jogador.setPorta(new Long(3698));
		jogador.setDataCadastro(new Date());
		jogador.setAtivo(true);
		manager.merge(jogador);

		
		
		trx.commit();
		manager.close();

	}

}
