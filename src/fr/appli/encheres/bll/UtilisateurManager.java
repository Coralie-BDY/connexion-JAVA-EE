package fr.appli.encheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.appli.encheres.bll.check.CheckUser;
import fr.appli.encheres.bo.Utilisateur;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.dao.DAOFactory;
import fr.appli.encheres.dal.dao.UtilisateurDAO;

public class UtilisateurManager {
		
	
	
	public UtilisateurManager() {
			
		}
		//CHERCHER TOUT LES UTILISATEURS
		public static List<Utilisateur> selectUsers() throws DALException {
			UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
			return userDAO.SelectAll();
		}
		
		//CHERCHER UN UTILISATEUR PAR SON PSEUDO
		public static Utilisateur selectUser(String user) throws  SQLException {
			UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
			Utilisateur searchingUser;
			searchingUser = null;
			try {
				searchingUser = userDAO.SelectByPseudo(user);
			} catch (DALException e) {
				e.printStackTrace();
			}
			return searchingUser;
		}
		
		//CHERCHER UN UTILISATEUR PAR SON ID
		public static Utilisateur selectUser(int no_utilisateur) throws SQLException {
			UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
			Utilisateur searchingUser;
			searchingUser = null;
			try {
				searchingUser = userDAO.SelectByNoUtilisateur(no_utilisateur);
			} catch (DALException e) {
				e.printStackTrace();
			}
			return searchingUser;
		}

		//INSERER UN UTILISATEUR DANS LA BDD
		public static boolean insertUser(Utilisateur user) throws SQLException, BllException {
			UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
			boolean bool = false;
			try {
				CheckUser.champs(user);
				userDAO.Insert(user);
				bool = true;
		
			} catch (DALException e) {
				e.printStackTrace();
				throw new BllException("Champ(s) invalide(s) lors de l'inscription");
			}
			return bool;
		}
	
		//SUPPRIMER UN UTILISATEUR DANS LA BDD
		public static void deleteUser(int no_utilisateur) throws SQLException, DALException {
			UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
			userDAO.Delete(no_utilisateur);
			
		}

		//CHANGER UN UTILISATEUR DANS LA BDD
		public static void updateUser(Utilisateur user) throws SQLException, DALException {
			UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
			try {
				CheckUser.champs(user);
				userDAO.update(user);
		
			} catch(DALException e) {
				e.printStackTrace();
				throw new BllException("Champ(s) invalide(s) lors de la modification");
			}
			
		} 
}
