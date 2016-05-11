package br.com.servicor.sd.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.servicor.sd.model.Carta;
import br.com.servicor.sd.service.CartaService;

@Named
@ViewScoped
public class CadastroCartaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Carta novaCarta;

	@Inject
	private CartaService cartaService;

	public void novaCarta() {
		novaCarta = new Carta();
	}

	public void inicializar() {

		novaCarta();
	}

	public Carta getNovaCarta() {
		return novaCarta;
	}

	public void setNovaCarta(Carta carta) {
		this.novaCarta = carta;
	}

	public void salvar() {

		cartaService.guardar(novaCarta);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, novaCarta.getDescricao() + " Cadastrado Com sucesso", ""));

		novaCarta();

	}

}
