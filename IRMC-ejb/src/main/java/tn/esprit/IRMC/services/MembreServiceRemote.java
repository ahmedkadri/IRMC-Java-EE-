package tn.esprit.IRMC.services;


import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.User;



@Remote
public interface MembreServiceRemote {
	public long Registration (User S);
	public void Update (User S);
    public String encrypt(String password);

}
