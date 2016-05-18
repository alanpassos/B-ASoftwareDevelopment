package br.com.servicor.sd.servidor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.servicor.sd.model.Jogador;

public class Cliente extends Thread {

	private Socket clientSocket;
	GenericFactory generic;

	public Cliente() {
		generic = new GenericFactory<>();
		
	}

	public void run() {
		try {
			clientSocket = new Socket("127.0.0.1", 6783);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			PrintStream print = new PrintStream(outToServer);
			print.print("jogador#todos");
			print.flush();
			print.close();

			BufferedInputStream inputS = new BufferedInputStream(clientSocket.getInputStream());
			ListarArquivoRecebido(inputS);

		} catch (Exception ex) {
			System.out.println("Aqui: "+ex.getMessage());
		}

	}

	private void ListarArquivoRecebido(BufferedInputStream inputS) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[1024];
		int read;

		String json = "";
		while ((read = inputS.read(buffer)) != -1) {

			json += buffer.toString();
		}

		List<Jogador> jogadores = generic.gerarLista(Jogador.class, json);

		for (Jogador jogador : jogadores) {
			System.out.println(jogador.getNome());
		}

	}



	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.start();

	}

}
