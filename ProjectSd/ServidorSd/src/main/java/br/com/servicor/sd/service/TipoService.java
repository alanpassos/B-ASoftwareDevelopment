package br.com.servicor.sd.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.model.Carta;
import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.model.Tipo;
import br.com.servicor.sd.repository.Baralhos;
import br.com.servicor.sd.repository.Cartas;
import br.com.servicor.sd.repository.Jogadores;
import br.com.servicor.sd.repository.Tipos;
import br.com.servicor.sd.util.jpa.Transacional;

public class TipoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Tipos tipos;

	@Transacional
	public Tipo guardar(Tipo tipo) {
		return tipos.guardar(tipo);
	}

	public Tipo porId(Long id) {
		return tipos.porId(id);
	}

	
	public List<Tipo> todos() {
		return tipos.todos();
	}

	
}
