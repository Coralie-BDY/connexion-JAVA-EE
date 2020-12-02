package fr.appli.encheres.bll;

import java.sql.SQLException;

import fr.appli.encheres.bll.check.CheckRetrait;
import fr.appli.encheres.bo.Retrait;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.dao.DAOFactory;
import fr.appli.encheres.dal.dao.RetraitDAO;

public class RetraitManager {

	//INSERTION DU LIEU DE RETRAIT
	public static boolean createRetrait(Retrait retrait) throws SQLException, BllException {
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
		boolean bool= false;
		try {
		CheckRetrait.champs(retrait);
		retraitDAO.insert(retrait);
		bool = true;
		}catch(DALException e) {
			e.printStackTrace();
			throw new BllException("Champ(s) invalide(s) lors de l'inscription");
		}
		return bool;
		
	}
	
	//CHERCHER UN LIEU DE RETRAI EN FONCTION DE L'ARTICLE
	public static Retrait searchRetrait(int noArticle) throws SQLException {
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
		Retrait retraitRecherche;
		retraitRecherche = null;
		try {
			retraitRecherche = retraitDAO.selectByNoArticle(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return retraitRecherche;
	}
	
	//CHANGER UN LIEU DE RETRAIT
	public static boolean changeRetrait(Retrait retrait) throws SQLException, BllException {
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
		boolean bool= false;
		try {
			CheckRetrait.champs(retrait);
			retraitDAO.update(retrait);
			bool = true;
		}catch (DALException e) {
			e.printStackTrace();
			throw new BllException("Champ(s) invalide(s) lors de l'inscription");
		}
		return bool;
		
	}

}
