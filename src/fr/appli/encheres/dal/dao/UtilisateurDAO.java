package fr.appli.encheres.dal.dao;

import java.util.List;

import fr.appli.encheres.bo.Utilisateur;
import fr.appli.encheres.dal.DALException;


public interface UtilisateurDAO {
	public Utilisateur SelectByPseudo(String pseudo) throws DALException;
	public Utilisateur SelectByNoUtilisateur(int no_utilisateur) throws DALException;
	public List<Utilisateur> SelectAll() throws DALException;
	public void Insert(Utilisateur utilisateur) throws DALException;
	public void update(Utilisateur utilisateur) throws DALException;
	public void Delete(int NoUtilisateur) throws DALException;
	
}
