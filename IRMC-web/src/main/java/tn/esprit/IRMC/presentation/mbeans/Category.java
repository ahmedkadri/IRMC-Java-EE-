package tn.esprit.IRMC.presentation.mbeans;



import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.IRMC.persistence.CategoryEvent;



@ManagedBean
@ApplicationScoped
public class Category {

	public CategoryEvent[] getCategoryEvents()
	{
		return CategoryEvent.values();
	}
	
}
