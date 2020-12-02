package fr.appli.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.appli.encheres.bll.check.CheckArticle;
import fr.appli.encheres.bo.ArticleVendu;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.dao.ArticleVenduDAO;
import fr.appli.encheres.dal.dao.DAOFactory;

public class ArticleVenduManager {
	//INSERTION DES ARTICLES
	public static boolean insertArticle(ArticleVendu article) throws SQLException, BllException {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		boolean bool= false;
		try {
			CheckArticle.champs(article);
			ArtDAO.insert(article);
			bool = true;
		}catch(DALException e) {
			e.printStackTrace();
			throw new BllException("Champ(s) invalide(s) lors de l'inscription");
		}
		return bool;
	}
	
	//RECUPERATION ARTICLE PAR SON NO_ARTICLE 
	public static ArticleVendu selectArticle(int no_article) throws SQLException {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		ArticleVendu searchingArticle = null;
		try {
			searchingArticle = ArtDAO.selectByNoArticle(no_article);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return searchingArticle;
	}
	
	//RECUPERATION ARTICLE PAR SON VENDEUR 
	public static List<ArticleVendu> selectVendeur(int no_utilisateur) {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		List<ArticleVendu> searchingSeller = new ArrayList<ArticleVendu>();
		searchingSeller = null;
		try {
			searchingSeller = ArtDAO.selectByVendeur(no_utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return searchingSeller;
	}
	
	
	//MODIFICATION ARTICLE
	public static boolean updateArticle(ArticleVendu article) throws SQLException {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		boolean up = false;
		try {
			CheckArticle.champs(article);
			ArtDAO.update(article);
			up = true;
			
		}catch(DALException e) {
			e.printStackTrace();
			throw new BllException("echec  mise a jour Article");
		}
		return up;
	}
	
	//RECUPERER LA LISTE DES ATICLES EN COURS DE VENTE 
	public static List<ArticleVendu> selectEcNoArticle() {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		List<ArticleVendu> ArticleList = new ArrayList<ArticleVendu>();
		ArticleList = null;
		try {
			ArticleList = ArtDAO.selectEcNoArticle();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return ArticleList;
	}

	//RECUPERER LA LISTE DES ATICLES EN COURS DE VENTE PAR NOM
	public static List<ArticleVendu> selectEcNomArticle(String nom_article) {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		List<ArticleVendu> ArticleList = new ArrayList<ArticleVendu>();
		ArticleList = null;
		try {
			ArticleList = ArtDAO.selectEcNomArticle(nom_article);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return ArticleList;
	}
	
	//RECUPERER LA LISTE DES ATICLES EN COURS DE VENTE PAR CATEGORIE
	public static List<ArticleVendu> selectEcNoCategorie(int no_categorie) {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		List<ArticleVendu> ArticleList = new ArrayList<ArticleVendu>();
		ArticleList = null;
		try {
			ArticleList = ArtDAO.selectEcNoCategorie(no_categorie);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return ArticleList;
	}

	//RECUPERER LA LISTE DES ATICLES EN COURS DE VENTE PAR NOM ET CATEGORIE
	public static List<ArticleVendu> selectEcNomArticleNoCategorie(String nom_article, int no_categorie) {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		List<ArticleVendu> ArticleList = new ArrayList<ArticleVendu>();
		ArticleList = null;
		try {
			ArticleList = ArtDAO.selectEcNomArticleNoCategorie(nom_article, no_categorie);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return ArticleList;
	}
	
	//RECUPERER UN ARTICLE PAR SON NOM
	public static ArticleVendu selectArticle(String nom_article) throws SQLException {
		ArticleVenduDAO ArtDAO = DAOFactory.getArticleVenduDAO();
		ArticleVendu searchingArticle = null;
		try {
			searchingArticle = ArtDAO.selectByArticle(nom_article);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchingArticle;
	}
	
}
