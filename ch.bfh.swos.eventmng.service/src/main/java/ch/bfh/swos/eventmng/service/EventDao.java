package ch.bfh.swos.eventmng.service;


import java.util.Collection;

import ch.bfh.swos.eventmng.model.Event;

public interface EventDao {
	
	public Event create();
	public Event read(long id);
	public Collection<Event> read();
	public Event update(Event book);
	public void delete(Event book);

}
