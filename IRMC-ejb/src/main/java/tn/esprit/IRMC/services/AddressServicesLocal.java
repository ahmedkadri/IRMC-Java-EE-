package tn.esprit.IRMC.services;



import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Library;
import tn.esprit.IRMC.persistence.LibraryAddress;



@Local
public interface AddressServicesLocal {

	

	public void AddAddress(LibraryAddress address);
	public void UpdateAddress(LibraryAddress address);
	public void RemoveAddress(LibraryAddress address);
	public LibraryAddress getAddressByid(int id);
	public LibraryAddress getAddressByLibrary(int libraryId);
	
	public void Assignlibrary(LibraryAddress address,Library l);
	
}
