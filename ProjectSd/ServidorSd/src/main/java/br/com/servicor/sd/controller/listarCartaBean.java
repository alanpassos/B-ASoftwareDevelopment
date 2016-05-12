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

import br.com.servicor.sd.model.Carta;
import br.com.servicor.sd.service.CartaService;

@Named
@ApplicationScoped
public class listarCartaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CartaService cartaService;

	private List<Carta> cartas;

	@PostConstruct
	public void inicializar() {
		cartas = cartaService.todos();
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public byte[] motrarImagem(String imagem) {

		DefaultStreamedContent imagemNew;

		imagemNew = new DefaultStreamedContent(new ByteArrayInputStream(Base64.decode(imagem)));

		return Base64.decode(imagem);

	}

}
