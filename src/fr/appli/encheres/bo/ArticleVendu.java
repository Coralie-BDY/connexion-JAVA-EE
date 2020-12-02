package fr.appli.encheres.bo;

import java.time.LocalDateTime;

/**
 * Classe ArticleVendu 
 * enumeration de l'état de vente
 * getters et setters
 * @author CoralieBDY
 *
 */

public class ArticleVendu {
	
	//ATTRIBUTS
	
	private int no_article;
	private String nom_article;
	private String description;
	private LocalDateTime date_debut_encheres;
	private LocalDateTime date_fin_encheres;
	private int prix_initial;
	private int prix_vente;
	private int no_utilisateur; //vendeur
	private int no_categorie;
	private String etat_vente;
	private String image;
	
	//CONSTRUCTEURS
	
	public ArticleVendu() {}
	
	public ArticleVendu(int no_article, String nom_article, String description,LocalDateTime date_debut_encheres, LocalDateTime date_fin_encheres, 
			int prix_initial, int prix_vente, int no_utilisateur, int no_categorie, String etat_vente, String image) {
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.etat_vente = etat_vente;
		this.image = image;
	}

	public ArticleVendu(String nom_article, String description,LocalDateTime date_debut_encheres, LocalDateTime date_fin_encheres, 
			int prix_initial, int prix_vente, int no_utilisateur, int no_categorie, String etat_vente, String image) {
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.etat_vente = etat_vente;
		this.image = image;
	}
	//GETTERS ET SETTERS 
	
	public int getNoArticle() {
		return no_article;
	}

	public void setNoArticle(int no_article) {
		this.no_article = no_article;
	}

	public String getNomArticle() {
		return nom_article;
	}

	public void setNomArticle(String nom_article) {
		this.nom_article = nom_article;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEncheres() {
		return date_debut_encheres;
	}

	public void setDateDebutEncheres(LocalDateTime date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return date_fin_encheres;
	}

	public void setDateFinEncheres(LocalDateTime date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}

	public int getPrixInitial() {
		return prix_initial;
	}

	public void setPrixInitial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

	public int getPrixVente() {
		return prix_vente;
	}

	public void setPrixVente(int prix_vente) {
		this.prix_vente = prix_vente;
	}
	
	public int getVendeur() {
		return no_utilisateur;
	}
	
	public void setVendeur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public int getNoCategorie() {
		return no_categorie;
	}
	public void setNoCategorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
	
	public String getEtatVente() {
		return etat_vente;
	}

	public void setEtatVente(String etat_vente) {
		this.etat_vente = etat_vente;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	@Override
	public String toString() {
		return String.format(
				"ArticleVendu [no_article=%s, nom_article=%s, description=%s, date_debut_encheres=%s, "
				+ "date_fin_encheres=%s, prix_initial=%s, prix_vente=%s, no-utilisateur=%s, no_categorie=%s, etat_vente=%s, image=%s]",
				no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,
				prix_vente, no_utilisateur, no_categorie, etat_vente, image);
	}

}
