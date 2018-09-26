package tn.esprit.IRMC.persistence;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.servlet.http.Part;

/**
 * Entity implementation class for Entity: Condidature
 *
 */

@NamedQueries({
	@NamedQuery(name="allCondidat",query="SELECT c FROM Condidature c")
})


@Entity

public class Condidature implements Serializable {

	private int idcondidat;
	private String namec;
	private String prenomc;
	private String mailc;
	private int numeroc;
	private Fonctiontype fonctioncc;

	private String filenamec;
	
	private int pourcentage;
	
	private File file;
	
	
	private static final long serialVersionUID = 1L;

	public Condidature() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdcondidat() {
		return idcondidat;
	}

	public void setIdcondidat(int idcondidat) {
		this.idcondidat = idcondidat;
	}

	public String getNamec() {
		return namec;
	}

	public void setNamec(String namec) {
		this.namec = namec;
	}

	public String getPrenomc() {
		return prenomc;
	}

	public void setPrenomc(String prenomc) {
		this.prenomc = prenomc;
	}

	public String getMailc() {
		return mailc;
	}

	public void setMailc(String mailc) {
		this.mailc = mailc;
	}

	public int getNumeroc() {
		return numeroc;
	}

	public void setNumeroc(int numeroc) {
		this.numeroc = numeroc;
	}

	public Fonctiontype getFonctioncc() {
		return fonctioncc;
	}

	public void setFonctioncc(Fonctiontype fonctioncc) {
		this.fonctioncc = fonctioncc;
	}

	
	
	
	

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilenamec() {
		return filenamec;
	}

	public void setFilenamec(String filenamec) {
		this.filenamec = filenamec;
	}
	

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	@Override
	public String toString() {
		return "Condidature [idcondidat=" + idcondidat + ", namec=" + namec + ", prenomc=" + prenomc + ", mailc="
				+ mailc + ", numeroc=" + numeroc + ", fonctioncc=" + fonctioncc + ", filenamec=" + filenamec
				+ ", pourcentage=" + pourcentage + "]";
	}


   
	
	
}
