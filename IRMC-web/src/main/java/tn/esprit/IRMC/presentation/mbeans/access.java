package tn.esprit.IRMC.presentation.mbeans;



import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.IRMC.persistence.TypeAcces;




@ManagedBean
@ApplicationScoped
public class access {

	public TypeAcces[] getAccess()
	{
		return TypeAcces.values();
	}
}
