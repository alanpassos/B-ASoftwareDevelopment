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
import br.com.servicor.sd.service.BaralhoService;
import br.com.servicor.sd.service.CartaService;

@Named
@ViewScoped
public class CadastroBaralhoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private BaralhoService cartaService;

	
	private Baralho novoBaralho;

	
	

	UploadedFile file;

	List<Carta> cartas;

	public void limpar() {
		novoBaralho = new Baralho();
		cartas = new ArrayList<Carta>();
	}

	public void inicializar() {

		limpar();
	}

	public Baralho getNovoBaralho() {
		return novoBaralho;
	}

	public void setNovoBaralho(Baralho baralho) {
		this.novoBaralho = baralho;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void uploadPhoto(FileUploadEvent e) throws IOException {

		file = e.getFile();
		System.out.println(file.getFileName());

		Carta carta = new Carta();
		carta.setDescricao(file.getFileName());
		carta.setNome(file.getFileName());
		carta.setPrincipal(false);
		carta.setCarta(file.getContents());
		cartas.add(carta);
		System.out.println("Carta: " + carta.getCarta().toString());
	}

	public void salvar() {
		novoBaralho.setCartas(cartas);
		cartaService.guardar(novoBaralho);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				novoBaralho.getNome() + " Cadastrado Com sucesso", ""));

		limpar();

	}

}
