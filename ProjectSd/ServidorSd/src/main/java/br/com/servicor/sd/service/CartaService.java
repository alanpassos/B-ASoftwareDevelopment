package br.com.servicor.sd.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.model.Carta;
import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.repository.Baralhos;
import br.com.servicor.sd.repository.Cartas;
import br.com.servicor.sd.repository.Jogadores;
import br.com.servicor.sd.util.jpa.Transacional;

public class CartaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Cartas cartas;

	@Inject
	private Baralhos baralhos;

	@Transacional
	public void guardar(List<Carta> cartas, Baralho baralho) {
	Baralho	baralhoNew = baralhos.guardar(baralho);
		for (Carta carta : cartas) {
			carta.setBaralho(baralhoNew);
			this.cartas.guardar(carta);
		}

	}

	public List<Carta> todos() {
		return cartas.todos();
	}

	public Carta porId(Long id) {
		return cartas.porId(id);
	}

}
