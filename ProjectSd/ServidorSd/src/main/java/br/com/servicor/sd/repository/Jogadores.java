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

import br.com.servicor.sd.model.Jogador;

public class Jogadores implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private CriteriaBuilder builder;
	private CriteriaQuery<Jogador> query;

	public Jogador guardar(Jogador jogador) {
		return manager.merge(jogador);
	}

	public List<Jogador> todos() {

		this.builder = this.manager.getCriteriaBuilder();
		this.query = builder.createQuery(Jogador.class);

		Root<Jogador> jogadorRoot = query.from(Jogador.class);
		Predicate predicate = builder.isTrue(jogadorRoot.get("ativo"));

		query.select(jogadorRoot);
		query.where(predicate);

		TypedQuery<Jogador> typedQuery = manager.createQuery(query);

		List<Jogador> jogadores = typedQuery.getResultList();

		return jogadores;

	}

	public Jogador porId(Long id) {

		this.builder = this.manager.getCriteriaBuilder();
		this.query = builder.createQuery(Jogador.class);

		Root<Jogador> jogadorRoot = query.from(Jogador.class);
		Predicate predicate = builder.and(
				builder.equal(jogadorRoot.get("id"), id),
				builder.isTrue(jogadorRoot.get("ativo")));

		query.select(jogadorRoot);
		query.where(predicate);

		TypedQuery<Jogador> typedQuery = manager.createQuery(query);

		Jogador jogador = typedQuery.getSingleResult();

		return jogador;

	}

}
