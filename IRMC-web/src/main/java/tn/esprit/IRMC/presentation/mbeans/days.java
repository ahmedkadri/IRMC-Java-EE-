package tn.esprit.IRMC.presentation.mbeans;



import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.IRMC.persistence.Day;




@ManagedBean
@ApplicationScoped
public class days {

	
	
	public Day[] getDays()
	{
		return Day.values();
	}
}
