package tn.esprit.IRMC.persistence;



import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: LibraryDomains
 *
 */
@Entity

public class LibraryDomains implements Serializable {

	

	private int id;
	private String domainName;

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	public LibraryDomains(String domainName) {
		super();
		this.domainName = domainName;
	}







	@Override
	public String toString() {
		return "LibraryDomains [id=" + id + ", domainName=" + domainName + "]";
	}







	private static final long serialVersionUID = 1L;

	public LibraryDomains() {
		super();
	}
   
}
