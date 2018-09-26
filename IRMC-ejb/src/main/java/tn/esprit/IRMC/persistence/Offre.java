package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Entity implementation class for Entity: Offre
 *
 */

@NamedQueries({
	@NamedQuery(name="allOffre",query="SELECT o FROM Offre o")
})

@Entity

public class Offre implements Serializable {

	private int idoffre;
	private String title;
	private String description;
	private OffreType offretype;
	private Date creationdate;
	private Date experationdate;
	
	private Quiz quiz2;
	
	private List<User> listusr = new ArrayList<>();
	
	
	private static final long serialVersionUID = 1L;

	public Offre() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdoffre() {
		return idoffre;
	}

	public void setIdoffre(int idoffre) {
		this.idoffre = idoffre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public OffreType getOffretype() {
		return offretype;
	}

	public void setOffretype(OffreType offretype) {
		this.offretype = offretype;
	}

	@Temporal(TemporalType.DATE)
	public Date getCreationdate() {
		return creationdate;
	}

	
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getExperationdate() {
		return experationdate;
	}

	public void setExperationdate(Date experationdate) {
		this.experationdate = experationdate;
	}

	//@JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	public Quiz getQuiz2() {
		return quiz2;
	}

	public void setQuiz2(Quiz quiz2) {
		this.quiz2 = quiz2;
	}
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER)
	public List<User> getListusr() {
		return listusr;
	}

	public void setListusr(List<User> listusr) {
		this.listusr = listusr;
	}

	@Override
	public String toString() {
		return "Offre [idoffre=" + idoffre + ", title=" + title + ", description=" + description + ", offretype="
				+ offretype + ", creationdate=" + creationdate + ", experationdate=" + experationdate + ", quiz2="
				+ quiz2 + "]";
	}

	
   
   
}
