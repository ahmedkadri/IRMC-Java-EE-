package tn.esprit.IRMC.persistence;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Library
 *
 */
@Entity

public class Library implements Serializable {

	private int LibraryId;
	private String Nom;
	private String LibrarySigle;
	private String LibraryWebSite;
	private String LibraryEmail;
	private Long LibraryPhoneNumer;
	
	private TypeAcces AccessType;
	private LibraryType type; 
	private Day Dayopening;
	private Day Dayclose;
	private Date TimeOpening;
	private Date TimeClose;
	private Double longitude;
	private Double altitude;
	
	private LibraryAddress address;
	private List<LibraryDomains> domains;
	
	
	
	
	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getLibraryId() {
		return LibraryId;
	}

	public void setLibraryId(int libraryId) {
		LibraryId = libraryId;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public List<LibraryDomains> getDomains() {
		return domains;
	}

	@Override
	public String toString() {
		return "Library [LibraryId=" + LibraryId + ", Nom=" + Nom + ", LibrarySigle=" + LibrarySigle
				+ ", LibraryWebSite=" + LibraryWebSite + ", LibraryEmail=" + LibraryEmail + ", LibraryPhoneNumer="
				+ LibraryPhoneNumer + ", AccessType=" + AccessType + ", type=" + type + ", Dayopening=" + Dayopening
				+ ", Dayclose=" + Dayclose + ", TimeOpening=" + TimeOpening + ", TimeClose=" + TimeClose
				+ ", longitude=" + longitude + ", altitude=" + altitude + ", address=" + address + ", domains="
				+ domains + "]";
	}

	public void setDomains(List<LibraryDomains> domains) {
		this.domains = domains;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getLibrarySigle() {
		return LibrarySigle;
	}

	public void setLibrarySigle(String librarySigle) {
		LibrarySigle = librarySigle;
	}

	public String getLibraryWebSite() {
		return LibraryWebSite;
	}

	public void setLibraryWebSite(String libraryWebSite) {
		LibraryWebSite = libraryWebSite;
	}

	public String getLibraryEmail() {
		return LibraryEmail;
	}

	public void setLibraryEmail(String libraryEmail) {
		LibraryEmail = libraryEmail;
	}

	public Long getLibraryPhoneNumer() {
		return LibraryPhoneNumer;
	}

	public void setLibraryPhoneNumer(Long libraryPhoneNumer) {
		LibraryPhoneNumer = libraryPhoneNumer;
	}


	@Enumerated(EnumType.STRING)
	public TypeAcces getAccessType() {
		return AccessType;
	}

	public void setAccessType(TypeAcces accessType) {
		AccessType = accessType;
	}


	@Enumerated(EnumType.STRING)
	public LibraryType getType() {
		return type;
	}

	public void setType(LibraryType type) {
		this.type = type;
	}


	@Enumerated(EnumType.STRING)
	public Day getDayopening() {
		return Dayopening;
	}

	public void setDayopening(Day dayopening) {
		Dayopening = dayopening;
	}


	@Enumerated(EnumType.STRING)
	public Day getDayclose() {
		return Dayclose;
	}

	public void setDayclose(Day dayclose) {
		Dayclose = dayclose;
	}

	public Date getTimeOpening() {
		return TimeOpening;
	}

	public void setTimeOpening(Date timeOpening) {
		TimeOpening = timeOpening;
	}

	public Date getTimeClose() {
		return TimeClose;
	}

	public void setTimeClose(Date timeClose) {
		TimeClose = timeClose;
	}

	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
	@OneToOne(cascade=CascadeType.REMOVE)
	public LibraryAddress getAddress() {
		return address;
	}

	public void setAddress(LibraryAddress address) {
		this.address = address;
	}

























	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}


	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Library(int libraryId, String nom, String librarySigle, String libraryWebSite, String libraryEmail,
			Long libraryPhoneNumer, TypeAcces accessType, LibraryType type, Day dayopening, Day dayclose,
			Date timeOpening, Date timeClose, Double longitude, Double altitude, LibraryAddress address) {
		super();
		LibraryId = libraryId;
		Nom = nom;
		LibrarySigle = librarySigle;
		LibraryWebSite = libraryWebSite;
		LibraryEmail = libraryEmail;
		LibraryPhoneNumer = libraryPhoneNumer;
		AccessType = accessType;
		this.type = type;
		Dayopening = dayopening;
		Dayclose = dayclose;
		TimeOpening = timeOpening;
		TimeClose = timeClose;
		this.longitude = longitude;
		this.altitude = altitude;
		this.address = address;
	}

	public Library(String nom, String librarySigle, String libraryWebSite, String libraryEmail, Long libraryPhoneNumer,
			TypeAcces accessType, LibraryType type, Day dayopening, Day dayclose, Date timeOpening, Date timeClose,
			Double longitude, Double altitude, LibraryAddress address) {
		super();
		Nom = nom;
		LibrarySigle = librarySigle;
		LibraryWebSite = libraryWebSite;
		LibraryEmail = libraryEmail;
		LibraryPhoneNumer = libraryPhoneNumer;
		AccessType = accessType;
		this.type = type;
		Dayopening = dayopening;
		Dayclose = dayclose;
		TimeOpening = timeOpening;
		TimeClose = timeClose;
		this.longitude = longitude;
		this.altitude = altitude;
		this.address = address;
	}




















	public Library(int libraryId, String nom, String librarySigle, String libraryWebSite, String libraryEmail,
			Long libraryPhoneNumer, TypeAcces accessType, LibraryType type, Day dayopening, Day dayclose,
			Date timeOpening, Date timeClose, Double longitude, Double altitude, LibraryAddress address,
			List<LibraryDomains> domains) {
		super();
		LibraryId = libraryId;
		Nom = nom;
		LibrarySigle = librarySigle;
		LibraryWebSite = libraryWebSite;
		LibraryEmail = libraryEmail;
		LibraryPhoneNumer = libraryPhoneNumer;
		AccessType = accessType;
		this.type = type;
		Dayopening = dayopening;
		Dayclose = dayclose;
		TimeOpening = timeOpening;
		TimeClose = timeClose;
		this.longitude = longitude;
		this.altitude = altitude;
		this.address = address;
		this.domains = domains;
	}

























	private static final long serialVersionUID = 1L;

	public Library() {
		super();
	}
   
}
