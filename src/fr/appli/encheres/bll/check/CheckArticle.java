package fr.appli.encheres.bll.check;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.appli.encheres.bll.BllException;
import fr.appli.encheres.bo.ArticleVendu;

public class CheckArticle {
	public static void champs(ArticleVendu article) throws BllException {
		String err = "";

		//NOM DE L'ARTICLE
		if ( !Check.alpha(article.getNomArticle()) || !Check.charMin(article.getNomArticle(),3)) { 
			err+="Le nom de l'article ne doit pas contenir de caracteres speciaux et doit contenir au moins 3 caracteres <br/>";
		}
			
		//CATEGORIES
		if (article.getNoCategorie() == 0 ) { 
			err+= "Veuillez choisir une catégorie.";
		}

		//PRIX INITIAL
		if (article.getPrixInitial() < 0){ 
			err+="Le prix de l'article ne doit pas être négatif <br/>";
		}
		
			
		//DATE OUVERTURE DE LA VENTE, FORMATAGE DE LA DATE
		
	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime open = article.getDateDebutEncheres();
		    
		if (open.isBefore(now)) { 
			err+="Date/heure d'ouverture non valide. Impossible de mettre une date/heure passée. <br/>";
		}
		
		if (err != "") {
			throw new BllException(err);
		}
	}


}
