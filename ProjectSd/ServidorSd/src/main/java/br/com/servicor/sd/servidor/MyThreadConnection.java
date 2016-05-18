package br.com.servicor.sd.servidor;

import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import br.com.servicor.sd.controller.listarBaralhoBean;
import br.com.servicor.sd.controller.listarJogadorBean;
import br.com.servicor.sd.metodo.AcessoMetodoUtil;
import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.repository.GenericFactory;
import br.com.servicor.sd.service.BaralhoService;
import br.com.servicor.sd.service.JogadorService;

public class MyThreadConnection extends Thread {
	private Socket s;

	GenericFactory generic;

	EntityManagerFactory factory;
	EntityManager manager;

	boolean navegador;

	public MyThreadConnection(Socket s) {
		this.s = s;
		this.navegador = false;
		generic = new GenericFactory<>();
		factory = Persistence.createEntityManagerFactory("ServidorSd");
		manager = factory.createEntityManager();

	}

	public void run() {
		try {

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			// Aguarda nome do arquivo enviado pelo cliente.
			try {
				// System.out.println("Setando timout servidor");
				s.setSoTimeout(50000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				s.close();
				JOptionPane.showMessageDialog(null, "Esgotado tempo limite");
				return;
			}
			String descricao = inFromClient.readLine();
			// System.out.println("Recebeu pedido de arquivo " + nmArquivo);
			System.out.println(descricao);

			OutputStream objectOutput = s.getOutputStream();

			acessarClassesBanco(descricao, objectOutput);

		} catch (IOException ex) {

		} catch (java.text.ParseException e) {
			// TODO: handle exception
		}

	}

	private void acessarMetodosBancoBaralho(String descricao, OutputStream objectOutput, Long id) {

		switch (descricao) {
		case "todos":

			getTodosBaralhos(objectOutput);

			break;

		case "porid":

			getBaralhoPorId(objectOutput, id);

			break;

		default:
			break;
		}

	}

	private void acessarMetodosBancoJogador(String descricao, OutputStream objectOutput, Long id)
			throws java.text.ParseException {

		switch (descricao) {
		case "todos":

			getTodosJogadores(objectOutput);

			break;

		case "porid":

			getJogadorPorId(objectOutput, id);

			break;

		default:
			break;
		}

	}

	private void acessarClassesBanco(String descricao, OutputStream objectOutput) throws java.text.ParseException {

		String[] descricaoTwo = descricao.split("#");

		switch (descricaoTwo[0]) {
		case "baralho":

			if (descricaoTwo.length < 3) {
				acessarMetodosBancoBaralho(descricaoTwo[1], objectOutput, new Long(0));
			} else {
				acessarMetodosBancoBaralho(descricaoTwo[1], objectOutput, new Long(descricaoTwo[2]));
			}

			break;

		case "carta":

			break;
		case "jogador":

			if (descricaoTwo.length < 3) {
				acessarMetodosBancoJogador(descricaoTwo[1], objectOutput, new Long(0));
			} else {
				acessarMetodosBancoJogador(descricaoTwo[1], objectOutput, new Long(descricaoTwo[2]));
			}

			break;
		default:
			break;
		}

	}

	public void getTodosBaralhos(OutputStream objectOutput) {

		
		
		
		List<Baralho> baralhos = manager.createQuery("from Baralho", Baralho.class).getResultList();
		manager.close();

		PrintStream print = new PrintStream(objectOutput);

		String baralhoNew = generic.returnJson(baralhos);
		print.print(baralhoNew);
		print.flush();
	}

	public void getBaralhoPorId(OutputStream objectOutput, Long id) {

		Baralho baralho = manager.find(Baralho.class, id);
		manager.close();

		PrintStream print = new PrintStream(objectOutput);

		String baralhoNew = generic.returnJson(baralho);
		print.print(baralhoNew);
		print.flush();
	}

	
	

	public void getTodosJogadores(OutputStream objectOutput) throws java.text.ParseException {

		List<Jogador> jogadores = manager.createQuery("from Jogador where JOG_ATIVO= true", Jogador.class)
				.getResultList();

		manager.close();

		PrintStream print = new PrintStream(objectOutput);

		String jogador = generic.returnJson(jogadores);
		print.print(jogador);
		print.flush();

	}

	public void getJogadorPorId(OutputStream objectOutput, Long id) {

		Jogador jogador = manager.find(Jogador.class, id);
		manager.close();

		PrintStream print = new PrintStream(objectOutput);

		String jogadorNew = generic.returnJson(jogador);
		print.print(jogadorNew);
		print.flush();
	}

}
