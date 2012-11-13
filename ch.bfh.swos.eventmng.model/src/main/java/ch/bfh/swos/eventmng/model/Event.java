package ch.bfh.swos.eventmng.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

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
	private Date dateFrom;
	private Date dateTo;
	private String ResponsiblePerson;
	private Integer EntryPrice;
	private static final long serialVersionUID = 1L;

	private List<Act> acts;

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
		return this.ResponsiblePerson;
	}

	public void setResponsiblePerson(String ResponsiblePerson) {
		this.ResponsiblePerson = ResponsiblePerson;
	}

	public Integer getEntryPrice() {
		return this.EntryPrice;
	}

	public void setEntryPrice(Integer EntryPrice) {
		this.EntryPrice = EntryPrice;
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

}
