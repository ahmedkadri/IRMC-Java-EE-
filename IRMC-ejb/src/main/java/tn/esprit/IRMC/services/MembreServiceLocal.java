package tn.esprit.IRMC.services;
import javax.ejb.Local;

import tn.esprit.IRMC.persistence.User;




@Local
public interface MembreServiceLocal {

	public long Registration (User S);
	public void Update (User S);
    public String encrypt(String password);

}
