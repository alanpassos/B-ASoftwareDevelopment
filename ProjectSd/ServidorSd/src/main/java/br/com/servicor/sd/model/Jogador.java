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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

@Entity
@SequenceGenerator(name = "SQ_JOGADOR", sequenceName = "SQ_JOGADOR", initialValue = 1, allocationSize = 1)
@Table(name = "SD_JOGADOR")
public class Jogador implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String nivel;
	private String host;
	private Long porta;
	private Long quantidadeVitoria;
	private Long quantidadeDerrota;
	private Date dataCadastro;
	private boolean ativo;

	private List<Baralho> baralhos;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_JOGADOR")
	@Column(name = "JOG_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "JOG_NOME", nullable = false, length = 50)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "JOG_NIVEL", nullable = false, length = 50)
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	@Column(name = "JOG_HOST", nullable = false, length = 50)
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name = "JOG_PORTA", nullable = false)
	public Long getPorta() {
		return porta;
	}

	public void setPorta(Long porta) {
		this.porta = porta;
	}

	
	@Column(name = "JOG_QUANTIDADE_VITORIA", nullable = true)
	public Long getQuantidadeVitoria() {
		return quantidadeVitoria;
	}

	public void setQuantidadeVitoria(Long quantidadeVitoria) {
		this.quantidadeVitoria = quantidadeVitoria;
	}

	
	@Column(name = "JOG_QUANTIDADE_DERROTA", nullable = true)
	public Long getQuantidadeDerrota() {
		return quantidadeDerrota;
	}

	public void setQuantidadeDerrota(Long quantidadeDerrota) {
		this.quantidadeDerrota = quantidadeDerrota;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	@Column(name = "JOG_DATA_CADASTRO", nullable = false)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@ColumnDefault(value = "true")
	@Column(name = "JOG_ATIVO")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
	
	@ManyToMany(mappedBy="jogadores",fetch = FetchType.EAGER)	
	public List<Baralho> getBaralhos() {
		return baralhos;
	}

	public void setBaralhos(List<Baralho> baralhos) {
		this.baralhos = baralhos;
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
		Jogador other = (Jogador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
