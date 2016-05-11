package br.com.servicor.sd.model;

public enum TipoBaralho {

	agressivo("Agressivo"), 
	equilibrado("Equilibrado"),
	defensivo("Defensivo");

	private String descricao;

	private TipoBaralho(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	

}
