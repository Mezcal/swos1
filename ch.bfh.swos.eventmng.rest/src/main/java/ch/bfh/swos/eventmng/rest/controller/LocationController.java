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

import ch.bfh.swos.eventmng.model.Location;
import ch.bfh.swos.eventmng.service.LocationDao;

/**
 * This class represents a rest controller for the locationsS
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
		System.out.println("Location created with id = " + createdLocation.getId());
		return createdLocation;
	}

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Location> getLocations() {
		System.out.println("Collection of Locations requested");
		return locationDao.read();
	}

	/**
	 * Read
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Location getLocation(@PathVariable long id) {
		System.out.println("Location requested with id = " + id);
		return locationDao.read(id);
	}

	/**
	 * Update
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Location updateLocation(@RequestBody Location location) {
		Location updatedLocation = locationDao.update(location);
		System.out.println("Location updated with id = " + updatedLocation.getId());
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
		System.out.println("Delete Location with id = " + id);
	}
}
