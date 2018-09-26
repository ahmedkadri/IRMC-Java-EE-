package tn.esprit.IRMC.services;



import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Library;
import tn.esprit.IRMC.persistence.LibraryAddress;


@Local
public interface LibraryServicesLocal {
	
	
	public void AddLibrary(Library l);
	public Library UpdateLibrary(Library l);
	public void RemoveLibrary(int id);
	public Library FindLibrarybyId(int id);
	public List<Library> FindALlLibraries();
	
	public void AssignDominsToLibrary(int id, int libraryid);

	public void AssignAddress(LibraryAddress address, Library library);
	
	public List<Library> findbyCountry(String country);
	public List<Library> findbyCity(String city);
	public Library findbySigle(String sigle);
	public Library findbyName(String Name);	
	
	
	public List<Library> findbyKeyWord(String keyword);
	

	
	public int getlength();
}
