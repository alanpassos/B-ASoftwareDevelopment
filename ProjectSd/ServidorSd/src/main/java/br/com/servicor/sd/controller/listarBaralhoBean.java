package br.com.servicor.sd.controller;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.Base64;

import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.model.Carta;
import br.com.servicor.sd.service.BaralhoService;
import br.com.servicor.sd.service.CartaService;

@Named
@ApplicationScoped
public class listarBaralhoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CartaService cartaService;

	@Inject
	private BaralhoService baralhoService;
	
	private List<Baralho> baralhos;

	@PostConstruct
	public void inicializar() {
	
		baralhos = baralhoService.todos();
		
	}


	public List<Baralho> getBaralhos() {
		return baralhos;
	}


	public byte[] getImagem(String imagem) {

		return Base64.decode(imagem);

	}

}
