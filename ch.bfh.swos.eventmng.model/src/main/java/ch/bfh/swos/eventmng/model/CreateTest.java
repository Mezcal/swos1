package ch.bfh.swos.eventmng.model;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

public class CreateTest {

	@Test
	public void test() {
		Event event = new Event();
		event.setName("event1");

		Act act1 = new Act();
		act1.setName("act1");

		Act act2 = new Act();
		act2.setName("act2");
		
		Act act3 = new Act();
		act2.setName("act3");

		ArrayList<Act> acts = new ArrayList<Act>();
		acts.add(act1);
		acts.add(act2);
		acts.add(act3);
		event.setActs(acts);

		ArrayList<Event> events = new ArrayList<Event>();
		events.add(event);
		act1.setEvents(events);
		act2.setEvents(events);

		EntityManager em = Persistence.createEntityManagerFactory(
				"ch.bfh.swos.eventmng.model").createEntityManager();

		em.getTransaction().begin();
		em.persist(event);
		em.getTransaction().commit();
	}
}
