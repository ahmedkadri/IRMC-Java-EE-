package tn.esprit.IRMC.services;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.User;



@Remote
public interface UserServiceRemote {

	public User addUser(User u);
	public void updateUser(User u);
	public void deleteUser(long id);
	public User findUserById(long id);
	public List<User> findUserByName(String name);
	public List<User> getAllUser();
	public User authentifier(String mail, String password );
	public void affecterCVUser(long iduser,int idcv);
	public User findMail(String mail);
	public List<User> RechercheDynamique( String name);
    
	public boolean DesactiverCompte(User u);
    
	public List<User> UserActive (String nom);
	
	public void banAccount(User u);
	public boolean confirmAccount(String token, User user);
	public List<User> getDesactiveUsers();
	
	
	public void updatepwd(User u,String pass);
	
	public String nbuseractive();
	public String nbuserbloque();
	public String nbuserdesactive();
}
