package fr.appli.encheres.dal.dao;

import fr.appli.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.appli.encheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.appli.encheres.dal.jdbc.RetraitDAOJdbcImpl;
import fr.appli.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO(){
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

}
