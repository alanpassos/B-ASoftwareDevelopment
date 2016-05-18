package br.com.servicor.sd.model;

public enum TipoBaralho {

	AGRESSIVO("Agressivo"), 
	EQUILIBRADO("Equilibrado"),
	DEFENSIVO("Defensivo");

	private String descricao;

	private TipoBaralho(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	

}
