package ch.bfh.swos.eventmng.service;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.bfh.swos.eventmng.model.Act;
import ch.bfh.swos.eventmng.model.Event;
import ch.bfh.swos.eventmng.model.Location;

/**
 * JUnit test class for testing the persistence
 * functionalities of the DAO-Objects
 * 
 * @author Mezcal
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContext.xml")
public class PersistenceServiceTest {

	@Inject
	private EventDao eventDao;
	@Inject
	private LocationDao locationDao;
	@Inject
	private ActDao actDao;
	
	@Test
	public void test() {
		// Test EventDao
		Event event = eventDao.create();
		event.setName("Event test");
		Event managedEvent = eventDao.update(event);
		Event foundEvent = eventDao.read(managedEvent.getId());
		Assert.assertTrue(event.getName().equals(foundEvent.getName()));
		eventDao.delete(foundEvent);
		
		// Test LocationDao
		Location loc = locationDao.create();
		loc.setName("Location test");
		Location managedLocation = locationDao.update(loc);
		Location foundLocation = locationDao.read(managedLocation.getId());
		Assert.assertTrue(loc.getName().equals(foundLocation.getName()));
		locationDao.delete(foundLocation);
		
		// Test ActDao
		Act act = actDao.create();
		act.setName("Act test");
		Act managedAct = actDao.update(act);
		Act foundAct = actDao.read(managedAct.getId());
		Assert.assertTrue(act.getName().equals(foundAct.getName()));
		actDao.delete(foundAct);
	}
	
}
