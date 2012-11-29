package ch.bfh.swos.eventmng.service;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import ch.bfh.swos.eventmng.model.Location;

@Named
public class JPALocationDao implements LocationDao {

	@PersistenceContext
	protected EntityManager em;
	
	public Location create() {
		return new Location();
	}

	public Location read(long id) {
		return em.find(Location.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Location> read() {
		return em.createQuery("select l from Location l").getResultList();
	}

	@Transactional
	public Location update(Location loc) {
		return em.merge(loc);
	}
	
	@Transactional
	public void delete(Location loc) {
		loc = em.merge(loc);
		em.remove(loc);
	}

}
