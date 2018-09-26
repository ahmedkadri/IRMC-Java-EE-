package tn.esprit.IRMC.services;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.IRMC.persistence.Etat;
import tn.esprit.IRMC.persistence.User;







/**
 * Session Bean implementation class UserServiceImpl
 */
@Stateless
public class UserServiceImpl implements UserServiceLocal,UserServiceRemote{

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
	
//	@EJB
	//CurriculumServiceLocal cvser;

	
    public UserServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    
    public String encrypt(String password) {
		String crypte = "";
		for (int i = 0; i < password.length(); i++) {
			int c = password.charAt(i) ^ 48;
			crypte = crypte + (char) c;
		}
		// System.out.println("token: " + crypte);
		return crypte;
	}

	public static String decrypt(String password) {
		String aCrypter = "";
		for (int i = 0; i < password.length(); i++) {
			int c = password.charAt(i) ^ 48;
			aCrypter = aCrypter + (char) c;
		}
		// System.out.println("token: " + aCrypter);
		return aCrypter;
	}

	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	
	@Override
	public User addUser(User u) {
		// TODO Auto-generated method stub
		em.persist(u);
		return u;
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
		
		em.merge(u);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		em.remove(findUserById(id));
	}

	@Override
	public User findUserById(long id) {
		// TODO Auto-generated method stub
		return em.find(User.class, id);

	}

	@Override
	public List<User> findUserByName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<User> query = em.createQuery("select d from User d where d.first_name like :n",User.class);
		query.setParameter("n", name);
       return query.getResultList();		
       
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select d from User d");
		return query.getResultList();	
	}


	
	@Override
	public User authentifier(String mail, String password) {
		User ens = null;
		String crypted_pwd = encrypt(password);
		TypedQuery<User> req = em.createQuery("select e from User e where e.mail = :m and e.password like :p",
				User.class);
		req.setParameter("m", mail).setParameter("p", crypted_pwd);

		try {
			ens = req.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("user inexistant");
		}
		return ens;

	}
	@Override
	
	public void affecterCVUser(long iduser, int idcv) {
		// TODO Auto-generated method stub
		User u = findUserById(iduser);
		
	//	CurriculumVitae cur = cvser.findCVById(idcv);
	//	u.setCV(cur);
		
		updateUser(u);
	}

	
	@Override
	public User findMail(String mail) {
		// TODO Auto-generated method stub
		TypedQuery<User> query = em.createQuery("select d from User d where d.mail like :n",User.class);
		query.setParameter("n", mail);
       return query.getSingleResult();	
	}

	@Override
	public List<User> RechercheDynamique(String name) {
		// TODO Auto-generated method stub
		TypedQuery<User> query = em.createQuery("select d from User d where d.last_name like :n",User.class);
		query.setParameter("n",  name);
       
		return query.getResultList();	
	}


	@Override
	public boolean DesactiverCompte(User u)
	{
		
		User u_desac = em.find(User.class, u.getIdUser());
		u_desac.setEtat(Etat.desactive);
		em.merge(u_desac);
		return true;
		
	}

	@Override
	public List<User> UserActive(String nom) {
		// TODO Auto-generated method stub
		TypedQuery<User> query = em.createQuery("select d from User d where d.etat='active' and last_name like :n",User.class);
		query.setParameter("n", nom);
       return query.getResultList();
	}

	@Override
	public void banAccount(User u) {
		// TODO Auto-generated method stub
		User us = em.find(User.class, u.getIdUser());
		us.setEtat(Etat.bloque);
		em.merge(us);
	}

	@Override
	public boolean confirmAccount(String token, User u) {
		// TODO Auto-generated method stub
		User us = em.find(User.class, u.getIdUser());
		String token_db = us.getActionToken();

		if (token.equals(token_db)) {
			us.setEtat(Etat.active);
			em.merge(us);
			System.out.println("token is valid ! Account is confirmed.");
			return true;
		} else {
			System.out.println("token not valid");
			return false;
		}	}

	@Override
	public List<User> getDesactiveUsers() {
		// TODO Auto-generated method stub
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u where u.etat='desactive'", User.class);
		return query.getResultList();	}


	@Override
	public void updatepwd(User u,String pass) {
		// TODO Auto-generated method stub
		
		String crypted_pwd = encrypt(pass);
		em.merge(u);

	}


	@Override
	public String nbuseractive() {
		// TODO Auto-generated method stub
	
        Query q = em.createQuery("SELECT COUNT(*) FROM User r where r.etat='active'");
        String result = q.getSingleResult().toString();
        		
        return result;
		
	}


	@Override
	public String nbuserbloque() {
		// TODO Auto-generated method stub
		 Query q = em.createQuery("SELECT COUNT(*) FROM User r where r.etat='desactive'");
	        String result = q.getSingleResult().toString();
	        		
	        return result;	}


	@Override
	public String nbuserdesactive() {
		// TODO Auto-generated method stub
		 Query q = em.createQuery("SELECT COUNT(*) FROM User r where r.etat='waitingForConfirmation'");
	        String result = q.getSingleResult().toString();
	        		
	        return result;	}


	@Override
	public List<User> getUserByOffre(int id) {
		// TODO Auto-generated method stub
		
		String sql = "select u from User u where u.ofr.idoffre =:id ";
		  Query query = em.createQuery(sql);
	        query.setParameter("id", id);
	        List<User> luss = query.getResultList();
		
		return luss ;
	}


	
    
}
