package br.com.servicor.sd.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.repository.Baralhos;
import br.com.servicor.sd.repository.Jogadores;
import br.com.servicor.sd.util.jpa.Transacional;

public class BaralhoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Baralhos baralhos;

	@Transacional
	public Baralho guardar(Baralho baralho) {
		
		baralho.setDataCadastro(new Date());
		return baralhos.guardar(baralho);
	}

	public List<Baralho> todos() {
		return baralhos.todos();
	}

	public Baralho porId(Long id) {
		return baralhos.porId(id);
	}

}
