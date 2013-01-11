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

import ch.bfh.swos.eventmng.model.Act;
import ch.bfh.swos.eventmng.service.ActDao;

/**
 * This class represents a rest controller for the acts
 * 
 * @author Mezcal
 *
 */
@Controller
@RequestMapping("/acts")
public class ActController {

	@Inject
	private ActDao actDao;

	/**
	 * Create
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Act createAct(@RequestBody Act act) {
		Act createdAct = actDao.update(act);
		return createdAct;
	}

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Act> getActs() {
		return actDao.read();
	}

	/**
	 * Read
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Act getAct(@PathVariable long id) {
		return actDao.read(id);
	}
	
	/**
	 * Get all acts, who have relations to an event (relations to an event make them undeletable)
	 * 
	 * @return a list of all dependent act objects
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/actsWithDependencies")
	@ResponseBody
	public List<Act> getActsWithDependencies() {
		return actDao.getActsWithDependencies();
	}

	/**
	 * Update
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Act updateAct(@RequestBody Act act) {
		Act updatedAct = actDao.update(act);
		return updatedAct;
	}

	/**
	 * Delete
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAct(@PathVariable long id) {
		Act act = actDao.read(id);
		actDao.delete(act);
	}
}
