package ch.bfh.swos.eventmng.service;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import ch.bfh.swos.eventmng.model.Event;

@Named
public class JPAEventDao implements EventDao {

	@PersistenceContext
	protected EntityManager em;
	
	public Event create() {
		return new Event();
	}

	public Event read(long id) {
		return em.find(Event.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Event> read() {
		return em.createQuery("select e from Event e").getResultList();
	}

	@Transactional
	public Event update(Event event) {
		return em.merge(event);
	}

	@Transactional
	public void delete(Event event) {
		event = em.merge(event);
		em.remove(event);
	}

}
