package br.com.servicor.sd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

@Entity
@SequenceGenerator(name = "SQ_BARALHO", sequenceName = "SQ_BARALHO", initialValue = 1, allocationSize = 1)
@Table(name = "SD_BARALHO")
public class Baralho implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private String nome;
	private TipoBaralho tipo;
	private Date dataCadastro;
	private boolean ativo;

	private List<Jogador> jogadores =  new ArrayList<Jogador>();
	private List<Carta> cartas = new ArrayList<Carta>();
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BARALHO")
	@Column(name = "BAR_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "BAR_DESCRICAO", nullable = false, length = 100)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "BAR_NOME", nullable = false, length = 100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "BAR_TIPO", nullable = false, length = 100)
	public TipoBaralho getTipo() {
		return tipo;
	}

	public void setTipo(TipoBaralho tipo) {
		this.tipo = tipo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	@Column(name = "BAR_DATA_CADASTRO", nullable = false)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@ColumnDefault(value = "true")
	@Column(name = "BAR_ATIVO", nullable = false)
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SD_JOGADOR_SD_BARALHO")
	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	@OneToMany(mappedBy="baralho")
	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
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
		Baralho other = (Baralho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}