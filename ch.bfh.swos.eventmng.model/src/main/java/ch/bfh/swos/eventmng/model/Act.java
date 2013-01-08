package ch.bfh.swos.eventmng.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Act
 * 
 * @author Mezcal
 *
 */
@Entity
public class Act implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;
	private String genre;
	private Integer salary;
	private static final long serialVersionUID = 1L;

	public Act() {
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

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getSalary() {
		return this.salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
}
