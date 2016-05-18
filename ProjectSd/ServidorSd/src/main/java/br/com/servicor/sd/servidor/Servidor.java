package br.com.servicor.sd.servidor;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.com.servicor.sd.model.Jogador;

public class Servidor extends Thread {
	/**
	 * @param args
	 * @throws IOException
	 */
	private int porta;
	
	private boolean servidorAtivo;
	ServerSocket welcomeSocket;

	public Servidor(int porta) {
		
		this.servidorAtivo = true;
		this.porta = porta;
	}

	public boolean isServidorAtivo() {
		return servidorAtivo;
	}

	public void desativarServidor() {
		try {
			this.welcomeSocket.close();
		} catch (IOException e) {
			
		}
		this.servidorAtivo = false;
	}

	@Override
	public void run() {
		// super.run();

		try {
			welcomeSocket = new ServerSocket(porta);
			while (true) {
				Socket connectionSocket = welcomeSocket.accept();
				
				if (connectionSocket != null) {
					MyThreadConnection c = new MyThreadConnection(connectionSocket);
					c.start();
				
				}
				
			}
		} catch (IOException e) {
			return;
		}

	}


	/*
	 * public static void main(String[] args) throws IOException { Servidor ser
	 * = new Servidor(6783); ser.start(); }
	 */
}
