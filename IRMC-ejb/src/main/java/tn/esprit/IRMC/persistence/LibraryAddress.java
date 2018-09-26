package tn.esprit.IRMC.persistence;



import java.io.Serializable;

import javax.jms.JMSSessionMode;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: LibraryAddress
 *
 */
@Entity

public class LibraryAddress implements Serializable {


	private int id;
	private String country;
	private String city;
	private int code;
	private String Description;
	
	private Library library;
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@OneToOne(mappedBy="address")
@JsonIgnore
	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	public LibraryAddress(String country, String city, int code, String description) {
		super();
		this.country = country;
		this.city = city;
		this.code = code;
		Description = description;
	}





	public LibraryAddress(int id, String country, String city, int code, String description) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.code = code;
		Description = description;
	
	}





	private static final long serialVersionUID = 1L;

	public LibraryAddress() {
		super();
	}
   
}
