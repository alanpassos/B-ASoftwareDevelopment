package br.com.servicor.sd.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.servicor.sd.model.Carta;
import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.service.CartaService;
import br.com.servicor.sd.service.JogadorService;

@Named
@ViewScoped
public class CadastroJogadorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Jogador novoJogador;

	@Inject
	private JogadorService jogadorService;

private	UploadedFile file;
	
	public void novoJogador() {
		novoJogador =new Jogador();
		
	}

	
	
	public UploadedFile getFile() {
		return file;
	}



	public void setFile(UploadedFile file) {
		this.file = file;
	}



	public void inicializar() {

		novoJogador();
	}

	public Jogador getNovoJogador() {
		return novoJogador;
	}

	public void setNovoJogador(Jogador jogador) {
		this.novoJogador = jogador;
	}

	public void salvar() {

		jogadorService.guardar(novoJogador);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, novoJogador.getNome() + " Cadastrado Com sucesso", ""));

		novoJogador();

	}

}
