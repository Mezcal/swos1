package ch.bfh.swos.eventmng.rest.controller;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ch.bfh.swos.eventmng.model.Location;
import ch.bfh.swos.eventmng.service.LocationDao;

/**
 * This class represents a rest controller for the locations
 * 
 * @author Mezcal
 *
 */
@Controller
@RequestMapping("/locations")
public class LocationController {

	@Inject
	private LocationDao locationDao;

	/**
	 * Create
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Location createLocation(@RequestBody Location location) {
		Location createdLocation = locationDao.update(location);
		return createdLocation;
	}

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Location> getLocations() {
		return locationDao.read();
	}

	/**
	 * Read
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Location getLocation(@PathVariable long id) {
		return locationDao.read(id);
	}
	
	/**
	 * Get all locations, who have relations to an event (relations to an event make them undeletable)
	 * 
	 * @return a list of all dependent location objects
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/locationsWithDependencies")
	@ResponseBody
	public List<Location> getLocationsWithDependencies() {
		return locationDao.getLocationsWithDependencies();
	}

	/**
	 * Update
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Location updateLocation(@RequestBody Location location) {
		Location updatedLocation = locationDao.update(location);
		return updatedLocation;
	}

	/**
	 * Delete
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteLocation(@PathVariable long id) {
		Location location = locationDao.read(id);
		locationDao.delete(location);
	}
}
