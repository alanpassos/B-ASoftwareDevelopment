package br.com.servicor.sd.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.util.Base64;

import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.model.Carta;
import br.com.servicor.sd.model.Tipo;
import br.com.servicor.sd.service.CartaService;
import br.com.servicor.sd.service.TipoService;

@Named
@ViewScoped
public class EditarCartaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CartaService cartaService;

	@Inject
	private TipoService tipoService;

	private List<Tipo> tipos;
	
	private Carta carta;

	public void limpar() {
		carta = new Carta();

	}

	public void inicializar() {

		limpar();
		
		tipos = tipoService.todos();
		
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	
	public byte[] getImagem(String imagem) {

		return Base64.decode(imagem);

	}
	
	
	public List<Tipo> getTipos() {
		return tipos;
	}

	public void salvar() {

		cartaService.guardar(carta);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, carta.getNome() + " Editado Com sucesso", ""));

		limpar();

	}

}
