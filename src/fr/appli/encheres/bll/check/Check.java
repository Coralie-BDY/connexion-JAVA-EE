package fr.appli.encheres.bll.check;

import java.text.Normalizer;




public abstract class Check {
/*VERIFICATION DES CARACTERES SPECIAUX
 RETOURNE VRAI SI CONTIENT QUE CHIFFRE ET LETTRES*/
	public static boolean alphaNum(String sample) {
		 String regExpression = "[a-zA-Z_0-9]*";
		 return sample.matches(regExpression);
	}

	
	/*VERIFICATION DES LETTRES
 RETOURNE VRAI SI QUE DES LETTRES */
	public static boolean alpha(String sample) {
		String regExpression = "[a-z- 'A-Z]*";
		 return sample.matches(regExpression);
	}
	
	public static boolean nomPrenom(String sample) {
		 String regExpression = "[a-z -'A-Z]*";
		 String temp=Normalizer.normalize(sample, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		 return temp.matches(regExpression);
	}
	public static boolean mail(String sample) {
		 String regExpression = "[a-z_.-@A-Z_0-9]*";
		 String at = "@";
		 if(sample.contains(at)) {
		 return sample.matches(regExpression);
		 }else {
			 return false;
		}
	}
	
	public static boolean rue(String sample) {
		 String regExpression = "[a-z' -/A-Z_0-9]*";
		 String temp=Normalizer.normalize(sample, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		 return temp.matches(regExpression);
	}
	public static boolean ville(String sample) {
		 String regExpression = "[a-z'/ A-Z]*";
		 String temp=Normalizer.normalize(sample, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		 return temp.matches(regExpression);
	}
	
//VERIFICATION DU NOMBRE DE CARATERE
	public static boolean nbChar(String sample, int nb) {
		if(sample.length()==nb) {
			return true;
		} else {
			return false;
		}
	}
	
//VERIFICATION DU MINIMUM DE CARACTERE A AVOIR
	public static boolean charMin(String sample, int min) {
		if(sample.length()<min) {
			return false;
		} else {
			return true;
		}
	}
	
//VERIFICATION DU MAXIMUM DE CARATERE A NE PAS DEPASSER
	public static boolean charMax(String sample, int max) {
		if(sample.length()>max) {
			return false;
		} else {
			return true;
		}
	}

	
}

