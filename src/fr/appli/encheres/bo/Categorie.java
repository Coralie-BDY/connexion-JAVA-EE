package fr.appli.encheres.bo;



public class Categorie {
	
	//ATTRIBUT
	
	private int no_categorie;
	private String libelle;

	// CONSTRUCTEUR
	
	public Categorie(int no_categorie, String libelle) {
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}
	
	public int getCategorie() {
		return no_categorie;
	}

	public void setNoCategorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	@Override
	public String toString() {
		return String.format("Categorie [no_categorie=%s, libelle=%s]", no_categorie, libelle);
	}
	
}
	
	
