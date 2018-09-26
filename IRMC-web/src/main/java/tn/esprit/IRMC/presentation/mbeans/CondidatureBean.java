package tn.esprit.IRMC.presentation.mbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

import org.primefaces.model.UploadedFile;

import tn.esprit.IRMC.persistence.Condidature;
import tn.esprit.IRMC.persistence.Fonctiontype;
import tn.esprit.IRMC.services.CondidatureServiceImplLocal;

@ManagedBean
@SessionScoped
public class CondidatureBean {
	
	@NotNull(message="manque nom")
	private String nomcondidatb;
	
	@NotNull(message="manque prenom")
	private String prenomcondidatb;
	
	@NotNull(message="manque mail")
	private String mailcondidatb;
	
	@NotNull(message="votre numero svp")
	private int numerocondidatb;
	
	private String fonctioncondidatb;
	
	@NotNull(message="votre CV svp")
	private String filenamecb;
	
	private int pourcentage;
	
	
	
	private Part file;
	private String fileContent;
	
	private UploadedFile filex;

	
	@EJB
	CondidatureServiceImplLocal col;
	
	 public void upload() {
		  
		    
		    
		    
		    filenamecb = filex.getFileName();
		    System.out.println(filenamecb);
		  }
	
	public String Postuler(){
		
		
		
		
		 
		Fonctiontype selectfnct = null;
		
		for(Fonctiontype f : Fonctiontype.values()){
			if(f.toString().equals(fonctioncondidatb)){
				selectfnct = f;
			}
		}
		
		System.out.println(selectfnct);
		
		
		
		Condidature CO = new Condidature();
		Condidature condcheck = new Condidature();
		
		CO.setNamec(nomcondidatb);
		CO.setPrenomc(prenomcondidatb);
		CO.setMailc(mailcondidatb);
		CO.setNumeroc(numerocondidatb);
		CO.setFonctioncc(selectfnct);
		
		CO.setFilenamec(fileContent);
		

		col.addCondidat(CO);

		
		//System.out.println(col.findCondidatureById(4));
		return null;
	}
	
	
	
	
	
	
	public String getNomcondidatb() {
		return nomcondidatb;
	}
	public void setNomcondidatb(String nomcondidatb) {
		this.nomcondidatb = nomcondidatb;
	}
	public String getPrenomcondidatb() {
		return prenomcondidatb;
	}
	public void setPrenomcondidatb(String prenomcondidatb) {
		this.prenomcondidatb = prenomcondidatb;
	}
	public String getMailcondidatb() {
		return mailcondidatb;
	}
	public void setMailcondidatb(String mailcondidatb) {
		this.mailcondidatb = mailcondidatb;
	}
	public int getNumerocondidatb() {
		return numerocondidatb;
	}
	public void setNumerocondidatb(int numerocondidatb) {
		this.numerocondidatb = numerocondidatb;
	}
	public String getFonctioncondidatb() {
		return fonctioncondidatb;
	}
	public void setFonctioncondidatb(String fonctioncondidatb) {
		this.fonctioncondidatb = fonctioncondidatb;
	}

	
	
	
	
	public String getFilenamecb() {
		return filenamecb;
	}

	public void setFilenamecb(String filenamecb) {
		this.filenamecb = filenamecb;
	}



	public int getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}






	public Part getFile() {
		return file;
	}






	public void setFile(Part file) {
		this.file = file;
	}






	public String getFileContent() {
		return fileContent;
	}






	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public UploadedFile getFilex() {
		return filex;
	}

	public void setFilex(UploadedFile filex) {
		this.filex = filex;
	}







	
	

}
