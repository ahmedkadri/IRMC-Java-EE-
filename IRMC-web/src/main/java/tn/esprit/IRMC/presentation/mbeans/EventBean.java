package tn.esprit.IRMC.presentation.mbeans;




import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.IRMC.persistence.CategoryEvent;
import tn.esprit.IRMC.persistence.Event;
import tn.esprit.IRMC.services.EventServiceLocal;




@ManagedBean
@SessionScoped
public class EventBean {
	
	@EJB
	EventServiceLocal es;
	
	private String title;
	private String subtitle;
	private String contactInfo;
	private String description;
	private Date startDate;
	private Date endDate;
	
	private String location;
	private String imageEvent;
	private boolean valide ;
	private boolean canceled;
	private int capacity;
	
	private boolean registered = false;
	private boolean notRegistered= true;
	
	private CategoryEvent categoryEvent;
	
	private List<Event> events;
	private List<Event> searchEvent=new ArrayList<>();
	
	private boolean etat;
	private Event event;
	private int idtoupdate;
	
	
	public int getIdtoupdate() {
		return idtoupdate;
	}

	public void setIdtoupdate(int idtoupdate) {
		this.idtoupdate = idtoupdate;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@PostConstruct
	public void init()
	{
		event=new Event();
		events=es.findAllEvents();
		etat=false;
		
		
		
	}
	
	public String addEvent()
	{
		es.addEvent(new Event(getTitle(),getSubtitle(),getContactInfo(),getLocation(),getDescription(),getCapacity(),getStartDate(),getEndDate(),getCategoryEvent()));
		
		return "/member/listEvent?faces-redirect=true";
		
		
	}
	/*
	public String addEvent()
	{
		es.addEvent(new Event(getTitle(),getSubtitle(),getContactInfo(),getDescription(),getStartDate(),getEndDate(),getCategoryEvent()));
		
	
		return "/member/listEvent?faces-redirect=true";
		
		
	}*/
	
	public String addEventAdmin()
	{
		es.addEvent(new Event(getTitle(),getSubtitle(),getContactInfo(),getLocation(),getDescription(),getCapacity(),getStartDate(),getEndDate(),getCategoryEvent()));
		
		
		return "/pages/admin/listEvent?faces-redirect=true";
		
		
	}
	
	
	public List<Event> getEvents(){
		events= es.findAllEvents();
		return events;
	}
	/*
	public void findEvent(){
		
		es.findEventById(event.getIdEvent());
	}
		
	*/
	
	public List<Event> getEventss(){
		
		events= es.findAllEventsMember();
		
		return events;
	}

	public void delete(int id)
	{
		
		es.deleteEvent(id);
		events=es.findAllEvents();
	}
	
	public String preEdit(Event e)
	{
		//etat = true;
		
	this.setTitle(e.getTitle());
		this.setSubtitle(e.getSubtitle());
		this.setContactInfo(e.getContactInfo());
		this.setLocation(e.getLocation());
		this.setDescription(e.getDescription());
		this.setStartDate(e.getStartDate());
		this.setEndDate(e.getEndDate());
		this.setCategoryEvent(e.getCategoryEvent());
		this.setIdtoupdate(e.getIdEvent());

		
		//	es.updateEvent(event);
		

		return "/pages/admin/modifEvent?faces-redirect=true";
	}
	
	public void edit()
	{
		es.updateEvent(new Event(getIdtoupdate(), getTitle(), getSubtitle(), getContactInfo(), getLocation(), getDescription(), getStartDate(), getEndDate(), getCategoryEvent()));
		//es.updateEvent(new Event(title, subtitle,));
		//events=es.findAllEvents();
		
	}

	public String Approver(int id) throws ParseException
	{
		Event e=es.findEventById(id);
		e.setValide(true);
		e.setCanceled(false);
		es.updateEvent(e);
		return "/admin/listEvent?faces-redirect=true";
		
	}
	
	public String Cancel(int id) throws ParseException
	{
		Event e=es.findEventById(id);
		e.setValide(false);
		e.setCanceled(true);
		es.updateEvent(e);
		return "/admin/listEvent?faces-redirect=true";
		
	}
	

	public String Participer(int id)
	{
		Event e=es.findEventById(id);
		e.setCapacity(e.getCapacity()-1);
		e.setNbPart(e.getNbPart()+1);
		es.updateEvent(e);
		return "/member/listEvent?faces-redirect=true";
	}
	
	
/*
	
	public void findEvent()
	{
		es.finfEventByTitle(event.getTitle());
		
		
	}*/
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CategoryEvent getCategoryEvent() {
		return categoryEvent;
	}
	public void setCategoryEvent(CategoryEvent categoryEvent) {
		this.categoryEvent = categoryEvent;
	}


/*	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
*/


	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public List<Event> getSearchEvent() {
		return searchEvent;
	}

	public void setSearchEvent(List<Event> searchEvent) {
		this.searchEvent = searchEvent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImageEvent() {
		return imageEvent;
	}

	public void setImageEvent(String imageEvent) {
		this.imageEvent = imageEvent;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public boolean isNotRegistered() {
		return notRegistered;
	}

	public void setNotRegistered(boolean notRegistered) {
		this.notRegistered = notRegistered;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	


	
	
}
