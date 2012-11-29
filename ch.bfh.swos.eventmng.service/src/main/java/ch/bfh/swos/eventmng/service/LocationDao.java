package ch.bfh.swos.eventmng.service;

import java.util.Collection;

import ch.bfh.swos.eventmng.model.Location;

/**
 * A location DAO supports the general persistence mechanisms 
 * for a location
 * 
 * @author Mezcal
 *
 */
public interface LocationDao {

	/**
	 * Create a new location
	 * 
	 * @return location
	 */
	public Location create();
	
	/**
	 * Get an location object
	 * 
	 * @param id of the location
	 * @return location with the given ID
	 */
	public Location read(long id);
	
	/**
	 * Get all persisted location objects
	 * 
	 * @return a collection of location objects
	 */
	public Collection<Location> read();
	
	/**
	 * Persist a location
	 * 
	 * @param location which should be persisted
	 * @return persisted location with its ID
	 */
	public Location update(Location loc);
	
	/**
	 * Delete a location
	 * 
	 * @param location which should be deleted
	 */
	public void delete(Location loc);
	
}
