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

public class Cartas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private CriteriaBuilder builder;
	private CriteriaQuery<Carta> query;

	public Carta guardar(Carta carta) {
		return manager.merge(carta);
	}

	public List<Carta> todos() {

		this.builder = this.manager.getCriteriaBuilder();
		this.query = builder.createQuery(Carta.class);

		Root<Carta> cartaRoot = query.from(Carta.class);
		Predicate predicate = builder.isTrue(cartaRoot.get("ativo"));

		query.select(cartaRoot);
		query.where(predicate);

		TypedQuery<Carta> typedQuery = manager.createQuery(query);

		List<Carta> cartas = typedQuery.getResultList();

		return cartas;

	}

	public Carta porId(Long id) {

		this.builder = this.manager.getCriteriaBuilder();
		this.query = builder.createQuery(Carta.class);

		Root<Carta> cartaRoot = query.from(Carta.class);
		Predicate predicate = builder.and(
				builder.equal(cartaRoot.get("id"), id),
				builder.isTrue(cartaRoot.get("ativo")));

		query.select(cartaRoot);
		query.where(predicate);

		TypedQuery<Carta> typedQuery = manager.createQuery(query);

		Carta carta = typedQuery.getSingleResult();

		return carta;

	}

}
