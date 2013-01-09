package ch.bfh.swos.eventmng.service;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	
	public List<Location> getLocationsWithDependencies(){
		try {
			TypedQuery<Location> query = em.createQuery("SELECT DISTINCT locations FROM Event e INNER JOIN e.locations AS locations", Location.class);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
