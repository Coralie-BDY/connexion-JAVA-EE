package fr.appli.encheres.bo;

public class Retrait {
	private int no_article;
	private String rue,code_postal,ville; 
	
	
	public Retrait() {}
	
	public Retrait(int no_article, String rue, String code_postal, String ville) {
		this.no_article = no_article;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}
	
	public int getNoArticle() {
		return no_article;
	}
	
	public void setNoArticle(int no_article) {
		this.no_article = no_article;
	}
	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return code_postal;
	}

	public void setCodePostal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return getNoArticle() + " " + getRue() + "\n" + getCodePostal() + " " + getVille();
	}
}
