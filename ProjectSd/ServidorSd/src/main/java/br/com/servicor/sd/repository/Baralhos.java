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
import br.com.servicor.sd.model.Jogador;

public class Baralhos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private CriteriaBuilder builder;
	private CriteriaQuery<Baralho> query;

	public Baralho guardar(Baralho baralho) {
		return manager.merge(baralho);
	}

	public List<Baralho> todos() {

		this.builder = this.manager.getCriteriaBuilder();
		this.query = builder.createQuery(Baralho.class);

		Root<Baralho> baralhoRoot = query.from(Baralho.class);
		Predicate predicate = builder.isTrue(baralhoRoot.get("ativo"));

		query.select(baralhoRoot);
		query.where(predicate);

		TypedQuery<Baralho> typedQuery = manager.createQuery(query);

		List<Baralho> baralhos = typedQuery.getResultList();

		return baralhos;

	}

	public Baralho porId(Long id) {

		this.builder = this.manager.getCriteriaBuilder();
		this.query = builder.createQuery(Baralho.class);

		Root<Baralho> baralhoRoot = query.from(Baralho.class);
		Predicate predicate = builder.and(
				builder.equal(baralhoRoot.get("id"), id),
				builder.isTrue(baralhoRoot.get("ativo")));

		query.select(baralhoRoot);
		query.where(predicate);

		TypedQuery<Baralho> typedQuery = manager.createQuery(query);

		Baralho baralho = typedQuery.getSingleResult();

		return baralho;

	}

}
