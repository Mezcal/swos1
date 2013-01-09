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
		System.out.println("Act created with id = " + createdAct.getId());
		return createdAct;
	}

	/**
	 * ReadAll
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Act> getActs() {
		System.out.println("Collection of Acts requested");
		return actDao.read();
	}

	/**
	 * Read
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Act getAct(@PathVariable long id) {
		System.out.println("Act requested with id = " + id);
		return actDao.read(id);
	}
	
	/**
	 * Check if has dependencies (whick make the object undeletable)
	 * @param actId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/actsWithDependencies")
	@ResponseBody
	public List<Act> getActsWithDependencies() {
		System.out.println("Collection of Acts WITH Dependencies requested ");
		return actDao.getActsWithDependencies();
	}

	/**
	 * Update
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Act updateAct(@RequestBody Act act) {
		Act updatedAct = actDao.update(act);
		System.out.println("Act updated with id = " + updatedAct.getId());
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
		System.out.println("Delete Act with id = " + id);
	}
}
