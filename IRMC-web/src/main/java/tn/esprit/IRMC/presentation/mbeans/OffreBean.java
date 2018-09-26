package tn.esprit.IRMC.presentation.mbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.New;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.primefaces.event.SelectEvent;

import tn.esprit.IRMC.persistence.Condidature;
import tn.esprit.IRMC.persistence.Offre;
import tn.esprit.IRMC.persistence.OffreType;
import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.CondidatureServiceImplLocal;
import tn.esprit.IRMC.services.OffreServiceImplLocal;
import tn.esprit.IRMC.services.OffreServiceImplRemote;
import tn.esprit.IRMC.services.UserServiceLocal;

@ManagedBean
@SessionScoped
public class OffreBean {
	
	@NotNull(message="Entrer Le Titre De l'Offre")
	private String titleb;
	
	@NotNull(message="Votre Description")
	private String descriptionb;
	
	private String offretypeb;
	
	@NotNull(message="la Date De Dreation est Obligatoire")
	@Future(message="date:invalide ('doit etre > ala date actuel')")
	private Date creationdateb;
	
	@NotNull(message="la Date D'Expiration est Obligatoire")
	@Future(message="Date:Invalide ('doit etre > ala date actuel')")
	private Date experationdateb;
	

	
	@EJB
	OffreServiceImplLocal osi;
	
	@EJB
	CondidatureServiceImplLocal col;
	
	@EJB
	UserServiceLocal usrservice;
	
	private Offre ofre;
	
	private boolean etat;
	
	private List<Offre> lesOffres = new ArrayList<>();
	
	private List<Offre> fiteroffre;
	
	private boolean VisibleQuiz;
	
	private int idoff;
	
	
	public List<Offre> getalloffre(){
		
		lesOffres = osi.getAllOffre();
	
		/*for(Offre o : lesOffres)
		{
			if(o.getOffretype().equals(OffreType.Scholarship)){
				this.setVisibleQuiz(true);
				System.out.println(this.VisibleQuiz);
			}else {
				this.setVisibleQuiz(true);
				System.out.println(this.VisibleQuiz);
			}
		}*/
		
		return lesOffres;
	}
	
	public String AddOffre(){
		
		if(creationdateb.compareTo(experationdateb)<0){
			OffreType selecttype = null;
			
			for(OffreType ola : OffreType.values()){
				if(ola.toString().equals(offretypeb)){
					selecttype = ola ;
				}
			}
			
			System.out.println(selecttype);
			
			Offre o = new Offre();
			o.setTitle(titleb);
			o.setDescription(descriptionb);
			o.setOffretype(selecttype);
			o.setCreationdate(creationdateb);
			o.setExperationdate(experationdateb);
			
			osi.addOffre(o);
			
			return "AllOffre?faces-redirect=true";
		}else {
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date D'expiration Doit etre Apres La Date Creation","Date D'expiration Doit etre Apres La Date Creation "));
			
			
			/*FacesMessage message =new FacesMessage
					("ERROR","Date D'expiration Doit etre Apres La Date Creation ");
		
			FacesContext.getCurrentInstance().
			addMessage("formregister:DateE", message);*/
			
			return null;
		}
		

	}
	
	/*public void getAllOffre(){
		return osi.getAllOffre();
	}*/
	
	public String removeOffre(Offre offre){
		osi.removeOffre(offre);
		return "AllOffre?faces-redirect=true";
	}
	

	
	public String redi_update(Offre o){
		etat = true;
		
		ofre=osi.findOffreById(o.getIdoffre());
		
		ofre.setTitle(o.getTitle());
		ofre.setDescription(o.getDescription());
		ofre.setOffretype(o.getOffretype());
		ofre.setCreationdate(o.getCreationdate());
		ofre.setExperationdate(o.getExperationdate());
		
		return "AllOffre?faces-redirect=true";
	}
	
	public String UpdateOffre(){
		etat = false;
		
		
		OffreType selecttype = null;
		
		for(OffreType ola : OffreType.values()){
			if(ola.toString().equals(offretypeb)){
				selecttype = ola ;
			}
		}
		
		ofre.setTitle(titleb);
		ofre.setDescription(descriptionb);
		ofre.setOffretype(selecttype);
		ofre.setCreationdate(creationdateb);
		ofre.setExperationdate(experationdateb);
		
		osi.ubdateOffre(ofre);
		return "AllOffre?faces-redirect=true";
	}
	
	
	public String redi_listcon(Offre o){
		etat = true;
		
		ofre=osi.findOffreById(o.getIdoffre());
		
		this.setIdoff(ofre.getIdoffre());
		
		ofre.setTitle(o.getTitle());
		ofre.setDescription(o.getDescription());
		ofre.setOffretype(o.getOffretype());
		ofre.setCreationdate(o.getCreationdate());
		ofre.setExperationdate(o.getExperationdate());
		
		return "listecondidat?faces-redirect=true";
	}
	
	private Offre o;
	
	 public List<User> lcond(){
		 
		
		 o = osi.findOffreById(this.idoff);
		 
		// List<Condidature> lcdd = new ArrayList<>();
		 List<User> luser = new ArrayList<>();
		 
		 luser = usrservice.getUserByOffre(o.getIdoffre());
		 //lcdd = o.getListecondat();
		 
		//lcdd = osi.getconbyoff(this.getIdoff()) ;
		 return luser;
	 }
	
	
	//************* partie front *********1233
	
	public Offre getO() {
		return o;
	}

	public void setO(Offre o) {
		this.o = o;
	}

	public List<Offre> getallCheckedoffre(){
		return osi.getAllCheckedOffre();
	}
	
	public String getTitleb() {
		return titleb;
	}
	public void setTitleb(String titleb) {
		this.titleb = titleb;
	}
	public String getDescriptionb() {
		return descriptionb;
	}
	public void setDescriptionb(String descriptionb) {
		this.descriptionb = descriptionb;
	}
	public String getOffretypeb() {
		return offretypeb;
	}
	public void setOffretypeb(String offretypeb) {
		this.offretypeb = offretypeb;
	}
	public Date getCreationdateb() {
		return creationdateb;
	}
	public void setCreationdateb(Date creationdateb) {
		this.creationdateb = creationdateb;
	}
	public Date getExperationdateb() {
		return experationdateb;
	}
	public void setExperationdateb(Date experationdateb) {
		this.experationdateb = experationdateb;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public Offre getOfre() {
		return ofre;
	}

	public void setOfre(Offre ofre) {
		this.ofre = ofre;
	}

	public List<Offre> getLesOffres() {
		return lesOffres;
	}

	public void setLesOffres(List<Offre> lesOffres) {
		this.lesOffres = lesOffres;
	}

	public List<Offre> getFiteroffre() {
		return fiteroffre;
	}

	public void setFiteroffre(List<Offre> fiteroffre) {
		this.fiteroffre = fiteroffre;
	}

	public boolean isVisibleQuiz() {
		return VisibleQuiz;
	}

	public void setVisibleQuiz(boolean visibleQuiz) {
		VisibleQuiz = visibleQuiz;
	}

	public int getIdoff() {
		return idoff;
	}

	public void setIdoff(int idoff) {
		this.idoff = idoff;
	}


	
	
	
}
