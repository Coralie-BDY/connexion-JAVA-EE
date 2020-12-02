package fr.appli.encheres.bo;

import java.time.LocalDateTime;


/**
 * Classe Enchere 
 * avec getters et setters 
 * @author CoralieBDY
 *
 */
public class Enchere {
	
	// ATTRIBUTS
	
	private int no_utilisateur; //encherisseur
	private int no_article, montant_enchere;
	private LocalDateTime date_enchere;
	
	public Enchere() {
		super();
	}
	
	public Enchere(int no_utilisateur,int no_article, int montant_enchere, LocalDateTime date_enchere) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.montant_enchere = montant_enchere;
		this.date_enchere = date_enchere; 
	}
	
	public int getEncherisseur(){
		return no_utilisateur;
	}
	
	public void setEncherisseur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}
	
	public int getNoArticle() {
		return no_article;
	}
	
	public void setNoArticle(int no_article) {
		this.no_article = no_article;
	}
	
	public int getMontantEnchere() {
		return montant_enchere; 
	}
	
	public void setMontantEnchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	public LocalDateTime getDateEnchere() {
		return date_enchere;
	}
	
	public void setDateEnchere(LocalDateTime date_enchere) {
		this.date_enchere = date_enchere;
	}
	
	
	//Methode
	@Override
	public String toString() {
		return String.format("Enchere [encherisseur=%s, no_article=%s, montant_enchere=%s, date_enchere=%s]", no_utilisateur, no_article,
				montant_enchere, date_enchere);
	}
}
