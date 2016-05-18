package br.com.servicor.sd.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.model.Carta;
import br.com.servicor.sd.model.Jogador;
import br.com.servicor.sd.model.Tipo;

public class Tipos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private CriteriaBuilder builder;
	private CriteriaQuery<Tipo> query;

	public Tipo guardar(Tipo tipo) {
		return manager.merge(tipo);
	}

	
	public List<Tipo> todos() {

		this.builder = this.manager.getCriteriaBuilder();
		this.query = builder.createQuery(Tipo.class);

		Root<Tipo> tipoRoot = query.from(Tipo.class);
		Predicate predicate = builder.isTrue(tipoRoot.get("ativo"));

		query.select(tipoRoot);
		query.where(predicate);

		TypedQuery<Tipo> typedQuery = manager.createQuery(query);

		List<Tipo> tipos = typedQuery.getResultList();

		return tipos;

	}

	
	
	
	public Tipo porId(Long id) {

		this.builder = this.manager.getCriteriaBuilder();
		this.query = builder.createQuery(Tipo.class);

		Root<Tipo> tipoRoot = query.from(Tipo.class);
		Predicate predicate = builder.and(builder.equal(tipoRoot.get("id"), id), builder.isTrue(tipoRoot.get("ativo")));

		query.select(tipoRoot);
		query.where(predicate);

		TypedQuery<Tipo> typedQuery = manager.createQuery(query);

		Tipo tipo = typedQuery.getSingleResult();

		return tipo;

	}

}
