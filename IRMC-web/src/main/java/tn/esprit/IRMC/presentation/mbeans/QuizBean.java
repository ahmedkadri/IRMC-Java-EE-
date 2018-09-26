package tn.esprit.IRMC.presentation.mbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Application;

import tn.esprit.IRMC.persistence.Condidature;
import tn.esprit.IRMC.persistence.Etat;
import tn.esprit.IRMC.persistence.Fonctiontype;
import tn.esprit.IRMC.persistence.Offre;
import tn.esprit.IRMC.persistence.OffreType;
import tn.esprit.IRMC.persistence.Question;
import tn.esprit.IRMC.persistence.Quiz;
import tn.esprit.IRMC.persistence.QuizType;
import tn.esprit.IRMC.persistence.Reponse;
import tn.esprit.IRMC.persistence.Role;
import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.CondidatureServiceImplLocal;
import tn.esprit.IRMC.services.OffreServiceImplLocal;
import tn.esprit.IRMC.services.OffreServiceImplRemote;
import tn.esprit.IRMC.services.QuestionServiceImplLocal;
import tn.esprit.IRMC.services.QuestionServiceImplRemote;
import tn.esprit.IRMC.services.QuizServiceImplLocal;
import tn.esprit.IRMC.services.QuizServiceImplRemote;
import tn.esprit.IRMC.services.ReponseServiceImplLocal;
import tn.esprit.IRMC.services.ReponseServiceImplRemote;
import tn.esprit.IRMC.services.UserServiceLocal;

@ManagedBean
@SessionScoped
public class QuizBean {

	@NotNull(message="Entrer Le Nom De La quiz")
	private String quiz_nomb;
	private String quiztypeb;
	
	@NotNull(message="Votre Question SVP")
	private String question_actualb;
	
	@NotNull(message="Votre Reponse ne doit pas étre Null")
	private String lareponseb;
	private Boolean correcteb;
	boolean convcor;
	
	int compteurR = 0;
	private int compteurQ = 0;
	private boolean verif1;
	private boolean verif2;
	
	private boolean visible;
	private boolean visibleTT = false;
	
	private boolean azerty ;
	
	
	private boolean etat;
	private boolean termin;
	
	private int Score = 0;
	
	private List<Integer> CheckQuestiona = new ArrayList<>();
	
	@NotNull(message="manque nom")
	private String nomcondidatb;
	
	@NotNull(message="manque prenom")
	private String prenomcondidatb;
	
	@NotNull(message="manque mail")
	private String mailcondidatb;
	
	@NotNull(message="votre numero svp")
	private int numerocondidatb;
	
	private String fonctioncondidatb;
	
	@NotNull(message="votre CV svp")
	private String filenamecb;
	
	private int pourcentage;
	
	@EJB
	CondidatureServiceImplLocal col;
	

	
	@EJB
	OffreServiceImplLocal osi;
	
	@EJB
	QuizServiceImplLocal qsr;
	
	@EJB
	QuestionServiceImplLocal qst;
	
	@EJB
	ReponseServiceImplLocal rsr;
	
	@EJB
	UserServiceLocal usrservice;

	private Offre ofre;

	private int id;
	private int idquiz;
	
	
	private String mail;
	private String password;
	private String pwdhash;
    private String token ;
	
    private User usr;
	
    
    public String authentification()
	{
		usr = usrservice.authentifier(mail,pwdhash);
		this.setUsr(usr);
		System.out.println(usr.getMail());

		if (usr != null) {
			
			 if(usr.getEtat()== Etat.active && usr.getRole()==Role.member)
		        {
				 System.out.println("******** validé***********");
		        return "ClientOffres2?faces-redirect=true";
		        }
			 if(usr.getEtat()==Etat.active && usr.getRole()==Role.webMaster)
		        {
		        	
		        	return "AllOffre?faces-redirect=true";
		        }
		        if(usr.getEtat()==Etat.bloque)
		        {
		        	FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("You are Blocked"));
		        }
		        
		        if(usr.getEtat()== Etat.waitingForConfirmation)
		        {
		        	return "/Confirm?faces-redirect=true";
		        }
		        
		        if(usr.getEtat()==Etat.desactive)
		        {
		        	FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Your account is note active"));
		        }
			}
			else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("User inexistant"));
			}
			return null;
			}
    
    
    
    
	public User getUsr() {
		return usr;
	}




	public void setUsr(User usr) {
		this.usr = usr;
	}




	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwdhash() {
		return pwdhash;
	}

	public void setPwdhash(String pwdhash) {
		this.pwdhash = pwdhash;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getIdquiz() {
		return idquiz;
	}

	public void setIdquiz(int idquiz) {
		this.idquiz = idquiz;
	}

	public String red2(Offre o2){
		
		if(o2.getQuiz2()!=null){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Cette Offre a deja un Quiz",
							"Cette Offre a deja un Quiz "));
			return null;
		}else{
			this.setId(o2.getIdoffre());		
			return "QuizHome?faces-redirect=true";
		}
		
		
	}
	
	public String addQuiz(){
		Offre o = new Offre();
		o = osi.findOffreById(this.getId());
		System.out.println(o.getTitle());
		
		QuizType qtype = null;
		for(QuizType qt :QuizType.values()){
			if(qt.toString().equals(quiztypeb)){
				qtype = qt;
			}
		}
		
		Quiz q = new Quiz();
		q.setQuiz_nom(quiz_nomb);
		q.setQuiztype(qtype);
		
		qsr.addquiz(q);
		//q= qsr.findQuizByID(id) find quiz by name
		q = qsr.findQuizByName(quiz_nomb);
		System.out.println(q.getQuiz_id());
		o.setQuiz2(q);
		osi.ubdateOffre(o);
		
		this.setIdquiz(q.getQuiz_id());
		this.setQuiz_nomb(quiz_nomb);
		//System.out.println(this.quiz_nomb);
		visible = true;
		return "QuestionAnswer?faces-redirect=true";
	}
	
	public List<Quiz> getAllQuiz(){
		return qsr.getAllQuiz();
	}
	
	public String removeQuiz(Quiz quiz){
		
		qsr.removeQuiz(quiz);
		return "AllQuiz?faces-redirect=true";
	}
	
	public String QuizAssign(Quiz quiz){
		System.out.println("******** id offre lil assign******\n");
		System.out.println(this.id);
		Offre o = new Offre();
		o = osi.findOffreById(this.id);
	
		quiz = qsr.findQuizByID(quiz.getQuiz_id());
		
		this.setIdquiz(quiz.getQuiz_id());
		
		o.setQuiz2(quiz);
		
		osi.ubdateOffre(o);
		return null;
	}
	
	public String ShowQuestion(){
		etat = true;
		this.setQuestion_actualb(question_actualb);
		System.out.println(this.getQuestion_actualb());
		Question q = new Question();
		q.setQuestion_actual(this.getQuestion_actualb());
		
		Quiz qz = new Quiz();
		qz = qsr.findQuizByID(this.getIdquiz());
		System.out.println("\n"+qz.getQuiz_id()+"***"+qz.getQuiz_nom());
		
		q.setQuiz3(qz);
		qst.addQuestion(q);
		compteurQ++;
		visible = false;
		
		if(compteurQ >3){
			visibleTT = true;
		}
		return "QuestionAnswer?faces-redirect=true";
	}
	
	public String AddReponse(){
		compteurR ++;
		Question qt = new Question();
		qt = qst.findQuestionByQuestion(question_actualb);
		System.out.println("\n"+qt.getQuestion_actual()+"***||***"+qt.getQuestion_id());
		
		Reponse r = new Reponse();
		convcor = correcteb.booleanValue();
		if(convcor == true)
			verif1 = true;
		r.setLareponse(lareponseb);
		r.setCorrecte(convcor);
		r.setQuestionx(qt);
		
		rsr.addReponse(r);
		if(compteurR>=3 && verif1 == true){
			this.verif2 = true;
		}
		
		return null;
	}
	
	public String Q_Suivant(){
		
		System.out.println(verif2);
		if(verif2 == true){
			etat = false;
			visible = true;
			System.out.println(" \n nombre de reponse verifier \n");
		return "QuestionAnswer?faces-redirect=true";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Il Faux au moins 3 Réponse et Cocher une D'elle",
							"Il Faux au moins 3 Réponse "));
			System.out.println("manque plus");
			return null;
		}
		
	}
	
	//*********** front ***********
	
	private int ID_Offre;
	
	
	
	public int getID_Offre() {
		return ID_Offre;
	}

	public void setID_Offre(int iD_Offre) {
		ID_Offre = iD_Offre;
	}

	public String red22(Offre o2){
		this.setId(o2.getIdoffre());
		this.setID_Offre(o2.getIdoffre());
		
		System.out.println(this.getId());
		
		return "TestQuiz2?faces-redirect=true";
	}
	
	public List<Question> testQuetion(){
		
	System.out.println("**************** lalalaalalal****************");
		List<Question> xx = null ;
		Offre o = new Offre();
		o = osi.findOffreById(this.getId());
		System.out.println(o.getTitle());
		Quiz Q = new Quiz();
		Q = o.getQuiz2();
		System.out.println( o.getQuiz2().getQuiz_nom());
		
		xx = Q.getListe_question();
		/*for (Question question : xx) {
			System.out.println(question);
		}*/
		
		return xx;
	}
	

	
	public void CheckQuestiona(ValueChangeEvent event){
		
		String[] icheck = (String[]) event.getNewValue();
		
		//Reponse rep2 = new Reponse();
		//rep2 = rsr.findReponseById(Integer.valueOf(icheck));
		
		//System.out.println(icheck);
		
		for(int i = 0; i < icheck.length ; i++ )
		{
			System.out.println(icheck[i]+"** lvaleur ya bouhmid********XXXXOOOOO	");
			CheckQuestiona.add(Integer.valueOf(icheck[i]));
		}
	}
	
	public String Resultat(){
		
		List<Reponse> reponse =new ArrayList<>();
		
	
		reponse = rsr.getAllReponse();
		
		
		for(Integer it :CheckQuestiona) {
			for(int i = 0 ; i < reponse.size() ; i++){
				if(it == reponse.get(i).getReponse_id() && reponse.get(i).isCorrecte() == true)
				{
					System.out.println("++++++++ Resultat S7i7a +++++++\n");
					System.out.println(reponse.get(i).getLareponse()+"egal ou pas "+it);
					System.out.println("id du rep correcte :: "+reponse.get(i).getReponse_id()+" : id de la rep choix:"+it);
					
					Score++;
					this.setScore(Score);
					
				}else {
					System.out.println("++++++++ Resultat Ghalta +++++++");
					//Score--;
					//this.setScore(Score);
				}
			}
		}
		
		if(this.getScore() < 0 )
		{
			this.setScore(0);
		}
		
		List<Question> LQ = new ArrayList<>();
		
		Offre o = new Offre();
		o = osi.findOffreById(this.getId());
		
		Quiz Q = new Quiz();
		Q = o.getQuiz2();
		
		
		LQ = Q.getListe_question();
		int NBQ = LQ.size(); 
		
		System.out.println("\n ****hetha Score :="+this.getScore()+" / "+NBQ);
		
		System.out.println("*********+++/*****"+this.getUsr().getMail()+"*********+++/*****");
		
		User u = new User();
		u = usrservice.findUserById(this.getUsr().getIdUser());
		System.out.println("0000000000000 "+u.getMail()+" 000000000000");
		
		int spp = (this.getScore()*100)/NBQ;
		
		u.setOfr(o);
		u.setPourcentage(spp);
		usrservice.updateUser(u);
		
		
		
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "signin?faces-redirect=true";
		
		//FacesContext.getCurrentInstance().addMessage(null, 
			//	new FacesMessage(FacesMessage.SEVERITY_ERROR, ," "));
		
	/*	Condidature CCC = new Condidature();
		CCC = col.findCondidatureByNum(this.getNumerocondidatb());
		CCC.setPourcentage(this.getScore());
		col.updateCondidat(CCC);
		System.out.println("condidat update score");*/
	}
	
	
	//****************** Condidature***************************
	
/*public String Postuler(){
	
	
		Offre o = new Offre();
		o = osi.findOffreById(this.getID_Offre());
		
		List<Condidature> lcc = new ArrayList<>();
		
		
		
		try {
			
			lcc = o.getListecondat();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ele est null");
		}
		
		if(lcc != null){
			
			System.out.println(lcc.toString());
		
		
		for (Condidature condidature : lcc) {
			
			if(condidature.getNumeroc() == numerocondidatb){
				System.out.println("numero du cond : "+condidature.getNumeroc());
				System.out.println("numero du cond test :"+numerocondidatb);
				System.out.println("**condidat existe in offre deja");
				azerty = true;
				System.out.println(azerty);
			}
			else {
				azerty = false;
				System.out.println(azerty);
			}
		}
		
		if(azerty == false){
			Fonctiontype selectfnct = null;
			
			for(Fonctiontype f : Fonctiontype.values()){
				if(f.toString().equals(fonctioncondidatb)){
					selectfnct = f;
				}
			}
			
			System.out.println(selectfnct);
			
			
			
			Condidature CO = new Condidature();
			Condidature condcheck = new Condidature();
			
			CO.setNamec(nomcondidatb);
			CO.setPrenomc(prenomcondidatb);
			CO.setMailc(mailcondidatb);
			CO.setNumeroc(numerocondidatb);
			CO.setFonctioncc(selectfnct);
			CO.setFilenamec(filenamecb);
			
			this.setNumerocondidatb(numerocondidatb);
			System.out.println(CO.toString());
			
			col.addCondidat(CO);
			
			System.out.println("condidat ajoute  wwwwwwwww");
			
			lcc.add(CO);
			o.setListecondat(lcc);
			osi.ubdateOffre(o);
			
			return "TestQuiz?faces-redirect=true";
		}
		
		
		
		}
		
		
	
		//System.out.println(col.findCondidatureById(4));
		return null;
	}*/
	
	
	
	
	
	public String getQuiz_nomb() {
		return quiz_nomb;
	}
	public void setQuiz_nomb(String quiz_nomb) {
		this.quiz_nomb = quiz_nomb;
	}
	public String getQuiztypeb() {
		return quiztypeb;
	}
	public void setQuiztypeb(String quiztypeb) {
		this.quiztypeb = quiztypeb;
	}


	public Offre getOfre() {
		return ofre;
	}


	public void setOfre(Offre ofre) {
		this.ofre = ofre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion_actualb() {
		return question_actualb;
	}

	public void setQuestion_actualb(String question_actualb) {
		this.question_actualb = question_actualb;
	}

	public String getLareponseb() {
		return lareponseb;
	}

	public void setLareponseb(String lareponseb) {
		this.lareponseb = lareponseb;
	}

	public Boolean getCorrecteb() {
		return correcteb;
	}

	public void setCorrecteb(Boolean correcteb) {
		this.correcteb = correcteb;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public boolean isVerif2() {
		return verif2;
	}

	public void setVerif2(boolean verif2) {
		this.verif2 = verif2;
	}

	public int getCompteurQ() {
		return compteurQ;
	}

	public void setCompteurQ(int compteurQ) {
		this.compteurQ = compteurQ;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisibleTT() {
		return visibleTT;
	}

	public void setVisibleTT(boolean visibleTT) {
		this.visibleTT = visibleTT;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public String getNomcondidatb() {
		return nomcondidatb;
	}

	public void setNomcondidatb(String nomcondidatb) {
		this.nomcondidatb = nomcondidatb;
	}

	public String getPrenomcondidatb() {
		return prenomcondidatb;
	}

	public void setPrenomcondidatb(String prenomcondidatb) {
		this.prenomcondidatb = prenomcondidatb;
	}

	public String getMailcondidatb() {
		return mailcondidatb;
	}

	public void setMailcondidatb(String mailcondidatb) {
		this.mailcondidatb = mailcondidatb;
	}

	public int getNumerocondidatb() {
		return numerocondidatb;
	}

	public void setNumerocondidatb(int numerocondidatb) {
		this.numerocondidatb = numerocondidatb;
	}

	public String getFonctioncondidatb() {
		return fonctioncondidatb;
	}

	public void setFonctioncondidatb(String fonctioncondidatb) {
		this.fonctioncondidatb = fonctioncondidatb;
	}

	public String getFilenamecb() {
		return filenamecb;
	}

	public void setFilenamecb(String filenamecb) {
		this.filenamecb = filenamecb;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}



	

	
	
	

	
}
