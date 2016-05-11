package br.com.servicor.sd.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.repository.Jogadores;
import br.com.servicor.sd.util.jpa.Transacional;

public class JogadorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Jogadores jogadores;

	@Transacional
	public Jogador guardar(Jogador jogador) {
		
		jogador.setDataCadastro(new Date());
		jogador.setAtivo(true);
		
		return jogadores.guardar(jogador);
	}

	public List<Jogador> todos() {
		return jogadores.todos();
	}

	public Jogador porId(Long id) {
		return jogadores.porId(id);
	}

}
