package br.com.servicor.sd.servidor;

import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.servicor.sd.metodo.AcessoMetodoUtil;

public class MyThreadConnection extends Thread {
	private Socket s;
	boolean navegador;

	public MyThreadConnection(Socket s) {
		this.s = s;
		this.navegador = false;
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

			ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());

			acessarClassesBanco(descricao, objectOutput);

		} catch (IOException ex) {

		}

	}

	private void acessarMetodosBancoBaralho(String descricao, ObjectOutputStream objectOutput, Long id) {

		switch (descricao) {
		case "todos":

			AcessoMetodoUtil.getTodosBaralhos(objectOutput);

			break;

		case "porId":

			AcessoMetodoUtil.getBaralhoPorId(objectOutput, id);

			break;

		default:
			break;
		}

	}

	private void acessarMetodosBancoJogador(String descricao, ObjectOutputStream objectOutput, Long id) {

		
		
		
		
		switch (descricao) {
		case "todos":

			AcessoMetodoUtil.getTodosJogadores(objectOutput);

			break;

		case "porId":

			AcessoMetodoUtil.getJogadorPorId(objectOutput, id);

			break;

		default:
			break;
		}

	}

	private void acessarClassesBanco(String descricao, ObjectOutputStream objectOutput) {

		String[] descricaoTwo = descricao.split(".");

		switch (descricaoTwo[0]) {
		case "Baralho":

			if (descricaoTwo.length < 3) {
				acessarMetodosBancoBaralho(descricaoTwo[1], objectOutput, new Long(0));
			} else {
				acessarMetodosBancoBaralho(descricaoTwo[1], objectOutput, new Long(descricaoTwo[2]));
			}

			break;

		case "Carta":

			break;
		case "Jogador":

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

}
