package tn.esprit.IRMC.services;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import 	javax.persistence.*;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import tn.esprit.IRMC.persistence.Library;
import tn.esprit.IRMC.persistence.LibraryAddress;



/**
 * Session Bean implementation class LibraryServices
 */
@Stateless
public class LibraryServices implements LibraryServicesLocal {

	@PersistenceContext
	EntityManager em;
	


	
	
	
    /**
     * Default constructor. 
     */
    public LibraryServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AddLibrary(Library l) {
		// TODO Auto-generated method stub
		em.persist(l);
	}

	@Override
	public Library UpdateLibrary(Library l) {
		// TODO Auto-generated method stub
		return em.merge(l);
	}

	@Override
	public void RemoveLibrary(int id) {
		// TODO Auto-generated method stub
		em.remove(FindLibrarybyId(id));
	}

	@Override
	public Library FindLibrarybyId(int id) {
		// TODO Auto-generated method stub
	
		
		return em.find(Library.class, id);
	}

	@Override

	public List<Library> FindALlLibraries() {
		// TODO Auto-generated method stub
	Query q = em.createQuery("select l from Library l",Library.class);
		
		return q.getResultList();
	}


	@Override
	public void AssignAddress(LibraryAddress address, Library library) {
		// TODO Auto-generated method stub
		library.setAddress(address);
		UpdateLibrary(library);
		
	}

	@Override
	public List<Library> findbyCountry(String country) {
		// TODO Auto-generated method stub
		Query q= em.createQuery("SELECT l FROM Library l WHERE l.address.country = :country");
		
		q.setParameter("country", country);
		
		return q.getResultList();
	}

	@Override
	public List<Library> findbyCity(String city) {
		// TODO Auto-generated method stub
Query q= em.createQuery("SELECT l FROM Library l WHERE l.address.city = :city");
		
		q.setParameter("city", city);
		
		return q.getResultList();
	}

	@Override
	public Library findbySigle(String sigle) {
		// TODO Auto-generated method stub
		Query q= em.createQuery("SELECT l FROM Library l WHERE l.librarySigle LIKE :sigle ",Library.class);
		
		q.setParameter("sigle","%"+sigle+"%");
		
		return (Library) q.getSingleResult();
	}

	@Override
	public Library findbyName(String Name) {
		// TODO Auto-generated method stub
		Query q= em.createQuery("SELECT l FROM Library l WHERE UPPER(l.nom) LIKE CONCAT('%',UPPER(:Name),'%')",Library.class);
		
		q.setParameter("Name", "%"+Name+"%");
		
		return (Library) q.getSingleResult();
	}

	@Override
	public List<Library> findbyKeyWord(String keyword) {
		// TODO Auto-generated method stub
		Query q= em.createQuery("SELECT l FROM Library l WHERE l.librarySigle LIKE :keyword OR l.address.city LIKE :keyword OR l.address.country LIKE :keyword  ",Library.class);
		
		q.setParameter("keyword","%"+keyword+"%");
		
		return q.getResultList();
	}



	@Override
	public int getlength() {
		// TODO Auto-generated method stub
	
		Query q = em.createQuery("select count(l) from Library l");
	
		return (int) q.getSingleResult();
	}

	@Override
	public void AssignDominsToLibrary(int id, int libraryid) {
		// TODO Auto-generated method stub
		
	}

}
