package ch.bfh.swos.eventmng.service;

import java.util.Collection;

import ch.bfh.swos.eventmng.model.Act;

/**
 * An act DAO supports the general persistence mechanisms 
 * for an act
 * 
 * @author Mezcal
 *
 */
public interface ActDao {
	
	/**
	 * Create a new act
	 * 
	 * @return act
	 */
	public Act create();
	
	/**
	 * Get an act object
	 * 
	 * @param id of the act
	 * @return act with the given ID
	 */
	public Act read(long id);
	
	/**
	 * Get all persisted act objects
	 * 
	 * @return a collection of act objects
	 */
	public Collection<Act> read();
	
	/**
	 * Persist an act
	 * 
	 * @param act which should be persisted
	 * @return persisted act with its ID
	 */
	public Act update(Act act);
	
	/**
	 * Delete an act
	 * 
	 * @param act which should be deleted
	 */
	public void delete(Act act);
	
}
