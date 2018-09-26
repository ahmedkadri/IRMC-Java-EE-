package tn.esprit.IRMC.services;

import java.util.Date;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.IRMC.persistence.Etat;
import tn.esprit.IRMC.persistence.Role;
import tn.esprit.IRMC.persistence.User;



/**
 * Session Bean implementation class SearcherService
 */
@Stateless
@LocalBean

public class MembreServiceImpl implements MembreServiceLocal, MembreServiceRemote {

	/**
	 * Default constructor.
	 */

	@PersistenceContext
	private EntityManager em;

	public MembreServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
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
	public long Registration(User S) {
		// TODO Auto-generated method stub
    
		String token = getSaltString();
		S.setActionToken(token);
		S.setEtat(Etat.active);
		S.setRole(Role.member);
		String pwd = encrypt(S.getPassword());
		S.setPassword(pwd);
		System.out.println(pwd);
		Date d = new Date();
		S.setDateinsription(d);
		if (S.getMail() == null  || S.getPassword() == null) {
			System.out.println("0");
			return 0;
		}
		else if (S.getPassword().length() < 4) {
			System.out.println("-2");
			return -2;
		}

		else if (S.getAddress() == null)
			return -5;

		em.persist(S);
		//SendConfirmationMail.sendMail("aya.benaissa@esprit.tn", "163JFT2012",
		//S.getMail(),
		//"IRMC team [Account confirmation code]", "This is your confirmation code :" +token);

		return S.getIdUser();	
		
	}

	@Override
	public void Update(User S) {
		// TODO Auto-generated method stub
		em.merge(S);

	}

	

}
