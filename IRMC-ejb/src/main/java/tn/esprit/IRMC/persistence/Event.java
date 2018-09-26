package tn.esprit.IRMC.persistence;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Event implements Serializable {


	private int idEvent;
	private String title;
	private String subtitle;
	private String contactInfo;
	private String location;
	private String description;
	private Date startDate;
	
	private Date endDate;
	private boolean valide = false;
	private boolean canceled= true;
	
	private String imageEvent;
	private boolean registered = false;
	private boolean notRegistered= true;
	private CategoryEvent categoryEvent;
	private User user;
	
	private int capacity;
	private int nbPart=0;
	

	private static final long serialVersionUID = 1L;

	
	

	public Event(boolean valide, boolean canceled) {
		super();
		this.valide = valide;
		this.canceled = canceled;
	}


	public Event(String title, String subtitle, String contactInfo, String description, Date startDate, Date endDate,
			 CategoryEvent categoryEvent) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.contactInfo = contactInfo;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.categoryEvent = categoryEvent;
		
	}


	public Event(String title, String subtitle, String contactInfo, String location, String description,int capacity ,Date startDate,
			Date endDate, CategoryEvent categoryEvent) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.contactInfo = contactInfo;
		this.location = location;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity=capacity;
		this.categoryEvent = categoryEvent;
	}
	
	



	public Event(String title, String subtitle, String contactInfo, String location, String description, Date startDate,
			Date endDate, boolean canceled, boolean notRegistered, CategoryEvent categoryEvent) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.contactInfo = contactInfo;
		this.location = location;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.canceled = canceled;
		this.notRegistered = notRegistered;
		this.categoryEvent = categoryEvent;
	}



	public Event() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

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

	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	@Enumerated(EnumType.STRING)
	public CategoryEvent getCategoryEvent() {
		return categoryEvent;
	}

	public void setCategoryEvent(CategoryEvent categoryEvent) {
		this.categoryEvent = categoryEvent;
	}

	
	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getImageEvent() {
		return imageEvent;
	}

	public void setImageEvent(String imageEvent) {
		this.imageEvent = imageEvent;
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


	public int getNbPart() {
		return nbPart;
	}


	public void setNbPart(int nbPart) {
		this.nbPart = nbPart;
	}


	public Event(int idEvent, String title, String subtitle, String contactInfo, String location, String description,
			Date startDate, Date endDate, CategoryEvent categoryEvent) {
		super();
		this.idEvent = idEvent;
		this.title = title;
		this.subtitle = subtitle;
		this.contactInfo = contactInfo;
		this.location = location;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.categoryEvent = categoryEvent;
	}

   
	
	
}
