package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Question
 *
 */

@NamedQueries({
	@NamedQuery(name="allQuestion",query="SELECT q FROM Question q")
})

@Entity

public class Question implements Serializable {

	private int question_id;
	private String question_actual;
	
	private Quiz quiz3;
	
	private List<Reponse> listeReponse ;
	
	private static final long serialVersionUID = 1L;

	public Question() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getQuestion_actual() {
		return question_actual;
	}

	public void setQuestion_actual(String question_actual) {
		this.question_actual = question_actual;
	}

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	public Quiz getQuiz3() {
		return quiz3;
	}

	public void setQuiz3(Quiz quiz3) {
		this.quiz3 = quiz3;
	}

	@OneToMany(mappedBy="questionx",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	public List<Reponse> getListeReponse() {
		return listeReponse;
	}

	public void setListeReponse(List<Reponse> listeReponse) {
		this.listeReponse = listeReponse;
	}

	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", question_actual=" + question_actual + ", quiz3=" + quiz3
				+ ", listeReponse=" + listeReponse + "]";
	}

	
   
	
	
}
