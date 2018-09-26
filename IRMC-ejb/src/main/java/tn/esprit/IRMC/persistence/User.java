
package tn.esprit.IRMC.persistence;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	
	private long idUser;
	
	private String first_name;
	
	private String last_name;
	private String mail	;
	private String password;
	private String phone_number	;
	private String address;
	private Date datenaissance ;
	private Date dateinsription;
	private Role	role;
	private String Image;
	
	//private CurriculumVitae CV;
	
	private String actionToken;
	
	private int pourcentage;
	private Offre ofr;
	
	private Etat etat;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	@Enumerated(EnumType.STRING)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Temporal(TemporalType.DATE)
	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}
	
	/*@OneToOne
	public CurriculumVitae getCV() {
		return CV;
	}

	public void setCV(CurriculumVitae cV) {
		CV = cV;
	}*/

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	@Temporal(TemporalType.DATE)
	public Date getDateinsription() {
		return dateinsription;
	}

	public void setDateinsription(Date dateinsription) {
		this.dateinsription = dateinsription;
	}

	public String getActionToken() {
		return actionToken;
	}

	public void setActionToken(String actionToken) {
		this.actionToken = actionToken;
	}
	
	@Enumerated(EnumType.STRING)
	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	public Offre getOfr() {
		return ofr;
	}

	public void setOfr(Offre ofr) {
		this.ofr = ofr;
	}
	
	
	private List<Reservation> listReservations;

	
	
	@OneToMany(mappedBy="owner")
	public List<Reservation> getListReservations() {
		return listReservations;
	}

	public void setListReservations(List<Reservation> listReservations) {
		this.listReservations = listReservations;
	}
	
	
	

/*	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", first_name=" + first_name + ", last_name=" + last_name + ", mail=" + mail
				+ ", password=" + password + ", phone_number=" + phone_number + ", address=" + address
				+ ", datenaissance=" + datenaissance + ", dateinsription=" + dateinsription + ", role=" + role
				+ ", Image=" + Image + ", CV=" + CV + ", actionToken=" + actionToken + ", etat=" + etat + "]";
	}*/

	
   
	
}
