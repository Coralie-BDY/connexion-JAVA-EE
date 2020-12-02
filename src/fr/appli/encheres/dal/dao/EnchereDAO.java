package fr.appli.encheres.dal.dao;

import java.util.List;

import fr.appli.encheres.bo.Enchere;
import fr.appli.encheres.dal.DALException;

public interface EnchereDAO {
	public Enchere selectByNoUtilisateur(int no_utilisateur) throws DALException;
	public List<Enchere> selectByNoArticle(int no_article) throws DALException;
	public List<Enchere> selectAll() throws DALException;
	public void insert(Enchere enchere) throws DALException;
	public void update(Enchere enchere) throws DALException;
	public void delete(int no_utilisateur, int no_article) throws DALException;
}
