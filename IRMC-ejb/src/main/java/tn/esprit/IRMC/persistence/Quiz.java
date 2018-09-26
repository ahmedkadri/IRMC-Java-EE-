package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Quiz
 *
 */

@NamedQueries({
	@NamedQuery(name="allquiz",query="SELECT q FROM Quiz q")
})

@Entity

public class Quiz implements Serializable {

	private int quiz_id;
	private String quiz_nom;
	private QuizType quiztype;
	
	private List<Offre> liste_offre = new ArrayList<Offre>() ;
	
	private List<Question> liste_question;
	
	private static final long serialVersionUID = 1L;

	public Quiz() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public String getQuiz_nom() {
		return quiz_nom;
	}

	public void setQuiz_nom(String quiz_nom) {
		this.quiz_nom = quiz_nom;
	}

	public QuizType getQuiztype() {
		return quiztype;
	}

	public void setQuiztype(QuizType quiztype) {
		this.quiztype = quiztype;
	}

	@JsonIgnore
	@OneToMany(mappedBy="quiz2",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	public List<Offre> getListe_offre() {
		return liste_offre;
	}

	public void setListe_offre(List<Offre> liste_offre) {
		this.liste_offre = liste_offre;
	}

	@OneToMany(mappedBy="quiz3",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	public List<Question> getListe_question() {
		return liste_question;
	}

	public void setListe_question(List<Question> liste_question) {
		this.liste_question = liste_question;
	}
   
	
}
