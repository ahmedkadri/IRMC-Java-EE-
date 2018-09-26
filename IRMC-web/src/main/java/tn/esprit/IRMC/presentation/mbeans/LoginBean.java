package tn.esprit.IRMC.presentation.mbeans;

import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.IRMC.persistence.Etat;
import tn.esprit.IRMC.persistence.Role;
import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.MembreServiceLocal;
import tn.esprit.IRMC.services.UserServiceLocal;





@ManagedBean
@SessionScoped
public class LoginBean {

	@EJB
	UserServiceLocal usrservice;
	
	@EJB
	MembreServiceLocal membreservice;
	

	
	private User usr;

	private String mail;
	private String password;
	private String pwdhash;
    private String token ;
		
	@PostConstruct
	public void init() {
		usr = new User();
		
	}
	
	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}



	public UserServiceLocal getUsrservice() {
		return usrservice;
	}

	public void setUsrservice(UserServiceLocal usrservice) {
		this.usrservice = usrservice;
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
	
	
	public String authentification()
	{
		usr = usrservice.authentifier(mail,pwdhash);
		this.setUsr(usr);
		System.out.println(usr.getMail());

		if (usr != null) {
			
			 if(usr.getEtat()== Etat.active && usr.getRole()==Role.member)
		        {
				 System.out.println("******** validé***********");
		        return "ClientOffres?faces-redirect=true";
		        }
			 if(usr.getEtat()==Etat.active && usr.getRole()==Role.webMaster)
		        {
		        	
		        	return "/back/index-2?facs-redirect=true";
		        }
		        if(usr.getEtat()==Etat.bloque)
		        {
		        	FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("You are Blocked"));
		        }
		        
		        if(usr.getEtat()== Etat.waitingForConfirmation)
		        {
		        	return "/Confirm?faces-redirect=true";
		        }
		        
		        if(usr.getEtat()==Etat.desactive)
		        {
		        	FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Your account is note active"));
		        }
			}
			else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("User inexistant"));
			}
			return null;
			}
	
	public String logOut(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/signin?faces-redirect=true";

	}

public String logOutAdmin(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/back/login?faces-redirect=true";

	}
	public String addUser() {
        initialiserDateInscription();
		membreservice.Registration(usr);
			usr = new User();
			return "/signin?faces-redirect=true";
		
	}
    
    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        usr.setDateinsription( date );
    }
	public String getPwdhash() {
		return pwdhash;
	}

	public void setPwdhash(String pwdhash) {
		this.pwdhash = pwdhash;
	}
	
	public void update(){
		usrservice.updateUser(usr);
		usrservice.findUserById(usr.getIdUser());
	}
	
	public void updatepass(String pwd){
		String pass=encrypt(pwd);
		usr.setPassword(pass);
		usrservice.updateUser(usr);

		usrservice.findUserById(usr.getIdUser());
	}
	

	
	public String Confirm(int id) {
		User us = new User();
		us.setIdUser(id);
		usrservice.confirmAccount(token, us);
		return "/profile?faces-redirect=true";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String encrypt(String password) {
		String crypte = "";
		for (int i = 0; i < password.length(); i++) {
			int c = password.charAt(i) ^ 48;
			crypte = crypte + (char) c;
		}
		return crypte;
	}
	
}
