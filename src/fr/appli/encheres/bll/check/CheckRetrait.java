package fr.appli.encheres.bll.check;

import fr.appli.encheres.bll.BllException;
import fr.appli.encheres.bo.Retrait;

public class CheckRetrait {
	public static void champs(Retrait retrait) throws BllException {
	String err = "";
	
		if (!Check.alpha(retrait.getRue()) || !Check.charMax(retrait.getRue(),50)){ 
			err+="La rue ne doit pas contenir de caracteres speciaux et doit contenir moins de 50 caracteres <br/>";
		}
		if (!Check.nbChar(retrait.getCodePostal(), 5)) { //!Check.num(retrait.getCodePostal()) || 	
			err+="Le code postal de l'utilisateur doit contenir 5 chiffres <br/>";
		}
		if (!Check.ville(retrait.getVille()) || !Check.charMax(retrait.getVille(), 50))	{
			err+="La ville de l'utilisateur ne doit contenir que des lettres et doit contenir moins de 50 caracteres <br/>";}
	
		if (err != "") {
			throw new BllException(err);
		}

	
	
	}

}
