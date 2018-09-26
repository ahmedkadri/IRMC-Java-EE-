package tn.esprit.IRMC.presentation.mbeans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.MembreServiceLocal;
import tn.esprit.IRMC.services.UserServiceLocal;



@ManagedBean
@SessionScoped
public class UserBean {
	
	private User usr;
	
	@EJB
	UserServiceLocal userService;

	@EJB
	MembreServiceLocal membreservice;
	
	
	String login;
	String Name;
	
	List<User> SearchActive;
	List<User> UserDesactive;
	List <User> RechercheUser ;
	
	List<User> ListUser = new ArrayList<>();
    List<User> Listuserrech = new ArrayList<>();
	
	private boolean etat;
	private	String nom;
	private String token ; 
	



	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<User> getSearchActive() {
		return SearchActive;
	}

	public void setSearchActive(List<User> searchActive) {
		SearchActive = searchActive;
	}

	public List<User> getUserDesactive() {
		return UserDesactive;
	}

	public void setUserDesactive(List<User> userDesactive) {
		UserDesactive = userDesactive;
	}

	public List<User> getRechercheUser() {
		return RechercheUser;
	}

	public void setRechercheUser(List<User> rechercheUser) {
		RechercheUser = rechercheUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<User> getListUser() {
		return ListUser;
	}

	public void setListUser(List<User> listUser) {
		ListUser = listUser;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

	public UserServiceLocal getUserService() {
		return userService;
	}

	public void setUserService(UserServiceLocal userService) {
		this.userService = userService;
	}


	public List<User> getListuserrech() {
		return Listuserrech;
	}

	public void setListuserrech(List<User> listuserrech) {
		Listuserrech = listuserrech;
	}

	@PostConstruct
	public void init() {
		usr = new User();
		ListUser = userService.getAllUser();
		etat=false;
		Listuserrech = userService.findUserByName(usr.getLast_name());
		SearchActive = userService.UserActive(usr.getLast_name());
		RechercheUser = userService.findUserByName(usr.getLast_name());
		
	}

	public String addUser() {
        initialiserDateInscription();

		 //userService.addUser(usr);
		membreservice.Registration(usr);
			usr = new User();

			return "/profile?faces-redirect=true";
		
	}

	
	public void findUser() {
		 userService.findUserByName(usr.getFirst_name());
			Listuserrech = userService.UserActive(nom);
	
	}
	

	public String preUpdate(User u){
		etat = true;
		usr = userService.findUserById(u.getIdUser());
		usr.setFirst_name(u.getFirst_name());
		usr.setLast_name(u.getLast_name());
		usr.setMail(u.getMail());
		usr.setDatenaissance(u.getDatenaissance());
		
		return null;
		
	}
	
	/*public void preUpdate1(User us) {
		User e = new User();
		etat = true;
		this.u = UL.findUserById(us.getIdUser());
		this.u.setAddresse(e.getAddresse());
		this.u.setMail(e.getMail());
		this.u.setBirthday(e.getBirthday());
		this.u.setLogin(e.getLogin());

	}*/
	
	public void update(){
		userService.updateUser(usr);
		userService.findUserById(usr.getIdUser());
		etat = false;
	}
	
	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	
    
    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        usr.setDateinsription( date );
    }

	
	public String desactive(int id)
	{
		User us = new User();
		us.setIdUser(id);
		userService.DesactiverCompte(us);
		return "/signin?faces-redirect=true";

	}
	
	public String Ban(int id) {
		User us = new User();
		us.setIdUser(id);
		userService.banAccount(us);
		return null;

	}
	
	public String remove(int id) {
		userService.deleteUser(id);
		ListUser = userService.getAllUser();
		return null;
	}
	
    
	public String Confirm(int id) {
		User us = new User();
		us.setIdUser(id);
		userService.confirmAccount(token, us);
		etat = true;
		return "/profile?faces-redirect=true";
	}

	/*public String etatcompte(){
		etat = true;

		usr.setEtat(Etat.waitingForConfirmation);
		userService.updateUser(usr);
		
		return null;
		
	}*/
}

