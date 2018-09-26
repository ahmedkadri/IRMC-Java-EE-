package tn.esprit.IRMC.services;



import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Event;



@Local
public interface EventServiceLocal {

	public void addEvent(Event e);
	public Event updateEvent(Event e);
	public void deleteEvent(int id);
	public Event findEventById(int id);
	public Event finfEventByTitle(String title);
	public List<Event> findAllEvents();
	public List<Event> findAllEventsMember();
	
}
