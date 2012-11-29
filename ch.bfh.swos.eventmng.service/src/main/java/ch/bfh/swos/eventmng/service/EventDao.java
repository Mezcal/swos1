package ch.bfh.swos.eventmng.service;


import java.util.Collection;

import ch.bfh.swos.eventmng.model.Event;

/**
 * An event DAO supports the general persistence mechanisms 
 * for an event
 * 
 * @author Mezcal
 *
 */
public interface EventDao {
	
	/**
	 * Create a new event
	 * 
	 * @return event
	 */
	public Event create();
	
	/**
	 * Get an event object
	 * 
	 * @param id of the event
	 * @return event with the given ID
	 */
	public Event read(long id);
	
	/**
	 * Get all persisted event objects
	 * 
	 * @return a collection of event objects
	 */
	public Collection<Event> read();
	
	/**
	 * Persist an event
	 * 
	 * @param event which should be persisted
	 * @return persisted event with its ID
	 */
	public Event update(Event event);
	
	/**
	 * Delete an event
	 * 
	 * @param event which should be deleted
	 */
	public void delete(Event event);

}
