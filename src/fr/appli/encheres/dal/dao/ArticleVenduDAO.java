package fr.appli.encheres.dal.dao;

import java.util.List;

import fr.appli.encheres.bo.ArticleVendu;
import fr.appli.encheres.dal.DALException;


public interface ArticleVenduDAO {
	public ArticleVendu selectByNoArticle(int no_article) throws DALException;
	public List<ArticleVendu> selectByVendeur(int no_utilisateur) throws DALException;
	public List<ArticleVendu> selectAll() throws DALException;
	public List<ArticleVendu> selectEcNoArticle() throws DALException;
	public List<ArticleVendu> selectEcNoCategorie(int no_categorie) throws DALException;
	public List<ArticleVendu> selectEcNomArticle(String nom_article) throws DALException;
	public List<ArticleVendu> selectEcNomArticleNoCategorie(String nom_article, int no_categorie) throws DALException;
	public void insert(ArticleVendu article) throws DALException;
	public void update(ArticleVendu article) throws DALException;
	public void delete(int no_article) throws DALException;
	public ArticleVendu selectByArticle(String nom_article) throws DALException;	
}
