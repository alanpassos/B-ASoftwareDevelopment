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
import br.com.servicor.sd.service.CartaService;

@Named
@ViewScoped
public class listarCartaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CartaService cartaService;

	private List<Carta> cartas;
	
	private Baralho baralho;

	
	public void inicializar() {

		if (baralho == null){
			baralho = new Baralho();
			
		}else{
			cartas = baralho.getCartas();
			
		}

		System.out.println(baralho.getId());
	}

	public void addBaralho(Baralho baralho) {
		this.baralho = baralho;
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}

	public byte[] motrarImagem(String imagem) {

		return Base64.decode(imagem);

	}

}
