package ch.bfh.swos.eventmng.service;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import ch.bfh.swos.eventmng.model.Act;

@Named
public class JPAActDao implements ActDao {

	@PersistenceContext
	protected EntityManager em;
	
	public Act create() {
		return new Act();
	}

	public Act read(long id) {
		return em.find(Act.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Act> read() {
		return em.createQuery("select a from Act a").getResultList();
	}

	@Transactional
	public Act update(Act act) {
		return em.merge(act);
	}

	@Transactional
	public void delete(Act act) {
		act = em.merge(act);
		em.remove(act);
	}

}
