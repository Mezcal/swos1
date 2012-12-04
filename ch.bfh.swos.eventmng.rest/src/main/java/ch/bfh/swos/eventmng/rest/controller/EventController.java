package ch.bfh.swos.eventmng.rest.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ch.bfh.swos.eventmng.model.Event;
import ch.bfh.swos.eventmng.service.EventDao;

/**
 * This class represents a rest controller for the events
 * 
 * @author Mezcal
 *
 */
@Controller
@RequestMapping("/events")
public class EventController {

	@Inject
	private EventDao eventDao;

	/**
	 * Create
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Event createEvent(@RequestBody Event event) {
		Event createdEvent = eventDao.update(event);
		System.out.println("Event created with id = " + createdEvent.getId());
		return createdEvent;
	}

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Event> getEvents() {
		System.out.println("Collection of Events requested");
		return eventDao.read();
	}

	/**
	 * Read
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Event getEvent(@PathVariable long id) {
		System.out.println("Event requested with id = " + id);
		return eventDao.read(id);
	}

	/**
	 * Update
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Event updateEvent(@RequestBody Event event) {
		Event updatedEvent = eventDao.update(event);
		System.out.println("Event updated with id = " + updatedEvent.getId());
		return updatedEvent;
	}

	/**
	 * Delete
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteEvent(@PathVariable long id) {
		Event event = eventDao.read(id);
		eventDao.delete(event);
		System.out.println("Delete Event with id = " + id);
	}
}
