package ch.bfh.swos.eventmng.service;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.bfh.swos.eventmng.model.Event;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContext.xml")
public class PersistenceServiceTest {

	@Inject
	private EventDao eventDao;
	
	@Test
	public void test() {
		Event event = eventDao.create();
		event.setName("event test");
		Event managedEvent = eventDao.update(event);
		Event foundEvent = eventDao.read(managedEvent.getId());
		Assert.assertTrue(event.getName().equals(foundEvent.getName()));
		eventDao.delete(foundEvent);
	}

}
