package fr.appli.encheres.dal.dao;

import java.util.List;

import fr.appli.encheres.bo.Retrait;
import fr.appli.encheres.dal.DALException;


public interface RetraitDAO {
	Retrait selectByNoArticle(int no_article) throws DALException;
	List<Retrait> selectAll() throws DALException;
	void insert(Retrait retrait) throws DALException;
	void update(Retrait retrait) throws DALException;
	void delete(int no_article) throws DALException;
}


