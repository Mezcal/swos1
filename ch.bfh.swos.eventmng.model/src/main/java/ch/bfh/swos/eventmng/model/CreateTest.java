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
		
		Location loc1 = new Location();
		loc1.setName("Wankdorf");
		
		Location loc2 = new Location();
		loc2.setName("Hallenstadion");

		ArrayList<Act> acts = new ArrayList<Act>();
		acts.add(act1);
		acts.add(act2);
		event.setActs(acts);

		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(loc1);
		locs.add(loc2);
		event.setLocations(locs);
		

		EntityManager em = Persistence.createEntityManagerFactory(
				"ch.bfh.swos.eventmng.model").createEntityManager();

		em.getTransaction().begin();
		em.persist(event);
		em.getTransaction().commit();
	}
}
