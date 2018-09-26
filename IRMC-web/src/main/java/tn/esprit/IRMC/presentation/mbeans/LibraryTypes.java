package tn.esprit.IRMC.presentation.mbeans;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import tn.esprit.IRMC.persistence.LibraryType;



@ManagedBean
@SessionScoped
public class LibraryTypes {

	
	
	public LibraryType[] getTypes()
	{
		return LibraryType.values();
	}
}
