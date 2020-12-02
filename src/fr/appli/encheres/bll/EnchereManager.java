package fr.appli.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.appli.encheres.bo.Enchere;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.jdbc.EnchereDAOJdbcImpl;

public class EnchereManager {
	
	
	
	public static Enchere creationEnchere(Enchere enchere) throws SQLException, DALException {
		List<Enchere> liste =  new ArrayList<Enchere>();
		Enchere encherir = new Enchere();
		encherir.setMontantEnchere(0);
		EnchereDAOJdbcImpl enchDAO = new EnchereDAOJdbcImpl();		
		liste = enchDAO.selectByNoArticle(enchere.getNoArticle());
		
	//VERIFICATION SI DEJA UNE ENCHERE
	if(liste.size()>0) {	
		
		//VERIFICATION DE LA HAUTEUR DE L'ENCHERE
		for (Enchere ench : liste) {	
			//CHERCHER SI IL Y A EU UEN ENCHERE D'UN UTILISATEUR
			if(ench.getEncherisseur()==enchere.getEncherisseur()) {
					suppressionEnchere(enchere.getEncherisseur(), enchere.getNoArticle());
				}
			//SI ENCHERE PLUS HAUTE L'UTISISATEUR D'AVANT RETROUVE SES CREDITS
			if(encherir.getMontantEnchere() < ench.getMontantEnchere()) {
				encherir = ench;
			}
		}
	}
	enchDAO.insert(enchere);
	//RECUPERATION DE LENCHERE 
	return encherir;
	}
	
	public static boolean suppressionEnchere(int no_utilisateur, int no_article) {
		EnchereDAOJdbcImpl enchDAO = new EnchereDAOJdbcImpl();	
		try {
			enchDAO.delete(no_utilisateur,no_article);
		}catch(DALException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
