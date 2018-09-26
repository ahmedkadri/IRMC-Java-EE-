package tn.esprit.IRMC.services;





import 	javax.persistence.*;

import tn.esprit.IRMC.persistence.Library;
import tn.esprit.IRMC.persistence.LibraryAddress;

import javax.ejb.EJB;

import javax.ejb.Stateless;




/**
 * Session Bean implementation class AddressServices
 */
@Stateless
public class AddressServices implements  AddressServicesLocal {

	@PersistenceContext
	EntityManager em;
	
	@EJB
	LibraryServicesLocal ls;
	
	
    /**
     * Default constructor. 
     */
    public AddressServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AddAddress(LibraryAddress address) {
		// TODO Auto-generated method stub
		em.persist(address);
	}

	@Override
	public void UpdateAddress(LibraryAddress address) {
		// TODO Auto-generated method stub
		em.merge(address);
	}

	@Override
	public void RemoveAddress(LibraryAddress address) {
		// TODO Auto-generated method stub
		em.remove(address);
	}

	@Override
	public LibraryAddress getAddressByid(int id) {
		// TODO Auto-generated method stub
		return 	em.find(LibraryAddress.class, id);
		
	}

	@Override
	public LibraryAddress getAddressByLibrary(int libraryId) {
		// TODO Auto-generated method stub
		Library l = ls.FindLibrarybyId(libraryId);		
		
		Query q = em.createQuery("select a from LibraryAddress a where a.Library.id = l.id ",LibraryAddress.class);		
		q.setParameter(libraryId, l.getLibraryId());
		
		return  (LibraryAddress) q.getSingleResult();
	}

	@Override
	public void Assignlibrary(LibraryAddress address, Library l) {
		// TODO Auto-generated method stub
		
		address.setLibrary(l);
		em.merge(address);
	}

}
