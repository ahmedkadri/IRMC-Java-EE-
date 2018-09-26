package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Response
 *
 */
@NamedQueries({
	@NamedQuery(name="allReponse",query="SELECT r FROM Reponse r")
})

@Entity

public class Reponse implements Serializable {

	private int reponse_id;
	private String lareponse;
	private boolean correcte;
	
	private boolean repcheck;
	
	private Question questionx;
	
	private static final long serialVersionUID = 1L;

	public Reponse() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getReponse_id() {
		return reponse_id;
	}

	public void setReponse_id(int reponse_id) {
		this.reponse_id = reponse_id;
	}

	public String getLareponse() {
		return lareponse;
	}

	public void setLareponse(String lareponse) {
		this.lareponse = lareponse;
	}

	public boolean isCorrecte() {
		return correcte;
	}

	public void setCorrecte(boolean correcte) {
		this.correcte = correcte;
	}

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	public Question getQuestionx() {
		return questionx;
	}

	public void setQuestionx(Question questionx) {
		this.questionx = questionx;
	}

	@Override
	public String toString() {
		return "Reponse [reponse_id=" + reponse_id + ", lareponse=" + lareponse + ", correcte=" + correcte + "]";
	}

	public boolean isRepcheck() {
		return repcheck;
	}

	public void setRepcheck(boolean repcheck) {
		this.repcheck = repcheck;
	}

	

	
   
	
}
