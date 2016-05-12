package br.com.servicor.sd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

@Entity
@SequenceGenerator(name = "SQ_CARTA", sequenceName = "SQ_CARTA", initialValue = 1, allocationSize = 1)
@Table(name = "SD_CARTA")
public class Carta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private String nome;
	private Long valorVidaTotal;;
	private boolean principal;
	private byte[] carta;
	private Date dataCadastro;
	private boolean ativo;

	Baralho baralho = new Baralho();
	List<Tipo> tipos;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CARTA")
	@Column(name = "CAR_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CAR_DESCRICAO", length = 100)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "CAR_NOME", length = 100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "CAR_VALOR_VIDA_TOTAL")
	public Long getValorVidaTotal() {
		return valorVidaTotal;
	}

	public void setValorVidaTotal(Long valorVidaTotal) {
		this.valorVidaTotal = valorVidaTotal;
	}

	@ColumnDefault(value = "false")
	@Column(name = "CAR_PRINCIPAL")
	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	@Column(name = "CAR_CARTA")
	public byte[] getCarta() {
		return carta;
	}

	public void setCarta(byte[] carta) {
		this.carta = carta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	@Column(name = "CAR_DATA_CADASTRO")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@ColumnDefault(value = "true")
	@Column(name = "CAR_ATIVO")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@ManyToOne
	@JoinColumn(name = "BAR_ID")
	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}

	@OneToMany
	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
