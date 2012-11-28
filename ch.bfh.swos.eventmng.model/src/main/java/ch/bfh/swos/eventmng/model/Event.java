package ch.bfh.swos.eventmng.model;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

/**
 * Entity implementation class for Entity: Event
 * 
 */
@Entity
public class Event implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;
	@Temporal(TIMESTAMP)
	private Date dateFrom;
	@Temporal(TIMESTAMP)
	private Date dateTo;
	private String responsiblePerson;
	private Integer entryPrice;
	private static final long serialVersionUID = 1L;

	@ManyToMany(cascade = { PERSIST, MERGE, REFRESH, DETACH })
	private List<Act> acts;
	
	@ManyToMany(cascade = { PERSIST, MERGE, REFRESH, DETACH })
	private List<Location> locations;

	public Event() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponsiblePerson() {
		return this.responsiblePerson;
	}

	public void setResponsiblePerson(String ResponsiblePerson) {
		this.responsiblePerson = ResponsiblePerson;
	}

	public Integer getEntryPrice() {
		return this.entryPrice;
	}

	public void setEntryPrice(Integer EntryPrice) {
		this.entryPrice = EntryPrice;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<Act> getActs() {
		return acts;
	}

	public void setActs(List<Act> acts) {
		this.acts = acts;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

}
