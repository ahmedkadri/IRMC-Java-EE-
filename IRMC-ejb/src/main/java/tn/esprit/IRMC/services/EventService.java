package tn.esprit.IRMC.services;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.IRMC.persistence.Event;



@Stateless
public class EventService  implements EventServiceLocal{

	@PersistenceContext
	EntityManager em;
	
	
	public EventService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addEvent(Event e) {
		// TODO Auto-generated method stub
		em.persist(e);
	}

	@Override
	public Event updateEvent(Event e) {
		// TODO Auto-generated method stub
		return em.merge(e);
	}

	@Override
	public void deleteEvent(int id) {
		// TODO Auto-generated method stub
		em.remove(findEventById(id));
	}

	@Override
	public Event findEventById(int id) {
		// TODO Auto-generated method stub
		return em.find(Event.class, id);
	}

	@Override
	public List<Event> findAllEvents() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("select e from Event e",Event.class);
		return q.getResultList();
	}

	@Override
	public Event finfEventByTitle(String title) {
		// TODO Auto-generated method stub
		TypedQuery<Event> q=em.createQuery("select e from Event e where e.title like :n", Event.class);
		q.setParameter("n", title);
		return q.getSingleResult();
	}

	@Override
	public List<Event> findAllEventsMember() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("select e from Event e where e.canceled=0",Event.class);
		return q.getResultList();
	}

}
