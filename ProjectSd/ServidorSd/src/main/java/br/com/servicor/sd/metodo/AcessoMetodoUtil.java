package br.com.servicor.sd.metodo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.inject.Inject;

import br.com.servicor.sd.controller.CadastroBaralhoBean;
import br.com.servicor.sd.controller.CadastroJogadorBean;
import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.service.BaralhoService;
import br.com.servicor.sd.service.JogadorService;

public class AcessoMetodoUtil {

	@Inject
	private static JogadorService jogadorService;

	@Inject
	private static BaralhoService baralhoService;

	public static void getTodosBaralhos(ObjectOutputStream objectOutput) {

		List<Baralho> baralhos = baralhoService.todos();
		try {

			for (Baralho baralho : baralhos) {

				objectOutput.writeObject(baralho);

			}

			objectOutput.flush();
			objectOutput.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getBaralhoPorId(ObjectOutputStream objectOutput, Long id) {

		Baralho baralho = baralhoService.porId(id);
		try {

			objectOutput.writeObject(baralho);

			objectOutput.flush();
			objectOutput.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setJogador(ObjectInputStream objectInput) {

		try {
			Jogador jogador = (Jogador) objectInput.readObject();

			jogadorService.guardar(jogador);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getTodosJogadores(ObjectOutputStream objectOutput) {

		List<Jogador> jogadores = jogadorService.todos();
		try {

			for (Jogador jogador : jogadores) {

				objectOutput.writeObject(jogador);

			}

			objectOutput.flush();
			objectOutput.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getJogadorPorId(ObjectOutputStream objectOutput, Long id) {

		Jogador jogador = jogadorService.porId(id);
		try {

			objectOutput.writeObject(jogador);

			objectOutput.flush();
			objectOutput.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
