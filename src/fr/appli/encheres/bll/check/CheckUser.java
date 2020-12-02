package fr.appli.encheres.bll.check;

import java.sql.SQLException;

import fr.appli.encheres.bll.BllException;
import fr.appli.encheres.bo.Utilisateur;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.dao.DAOFactory;
import fr.appli.encheres.dal.dao.UtilisateurDAO;

public class CheckUser {

	public static void champs(Utilisateur user) throws BllException {
		String err="";
		if (!Check.nomPrenom(user.getNom()) || !Check.charMin(user.getNom(),3)) { 
			err+="Le nom de l'utilisateur ne doit contenir que des lettres et doit contenir au moins 3 caracteres <br/>";
		}
		if (!Check.nomPrenom(user.getPrenom()) || !Check.charMin(user.getNom(),3)) { 
			err+="Le prenom de l'utilisateur ne doit contenir que des lettres et doit contenir au moins 3 caracteres <br/>"; 
		}
		if (!Check.mail(user.getEmail()) || !Check.charMax(user.getEmail(),20))	{ 
			err+="L'adresse mail de l'utilisateur doit contenir moins de 20 caracteres <br/>";
		}
//		if ((!Check.nbChar(user.getTelephone(),10)) ) { //!Check.num(user.getTelephone()) || 
//			err+="Le numero de telephone doit contenir 10 chiffres <br/>";
//		}
		if (!Check.rue(user.getRue()) || !Check.charMax(user.getRue(),50)) { 
			err+="L'adresse ne doit pas contenir de caracteres speciaux et doit contenir moins de 50 caracteres <br/>";
		}
//		if ( !Check.nbChar(user.getCodePostal(), 5)) { //!Check.num(user.getCodePostal()) ||
//			err+="Le code postal de l'utilisateur doit contenir 5 chiffres <br/>";
//		}
		if (!Check.ville(user.getVille()) || !Check.charMax(user.getVille(), 50)) {
			err+="La ville de l'utilisateur ne doit contenir que des lettres et doit contenir moins de 50 caracteres <br/>";
		}
		if (!Check.charMin(user.getMotDePasse(),8))	{ 
			err+="Le mot de passe doit contenir au moins 8 caracteres <br/>";
		}
		
		if (err != "") {
			throw new BllException(err);
		}
	}
	
	public static boolean userExiste(Utilisateur userTest) throws SQLException {
		boolean testUser = false;
		
		if (userTest != null) {
			testUser = true;
		} else {
			System.err.println("utilisateur null");
		}
		return testUser;
	}
	
	public static boolean mdpOk (Utilisateur  userTest, String mdpTest) throws SQLException {
		boolean testMdp = false;
		
		if(mdpTest.equals(userTest.getMotDePasse())){
			testMdp = true;
		} else {
			System.err.println("erreur mdpOk");
		}
		return testMdp;
	}
	
	
	public static boolean identificationOk(String pseudo, String mdpTest) throws SQLException {
		boolean testOk = false;
		boolean testUser = false;
		boolean testMdp = false;
		
		UtilisateurDAO daoUtilisateur = DAOFactory.getUtilisateurDAO();
		Utilisateur utilisateurTest = null;
		
		try {
			utilisateurTest = daoUtilisateur.SelectByPseudo(pseudo);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		testUser = userExiste(utilisateurTest);
		
		if (testUser){
			testMdp = mdpOk(utilisateurTest, mdpTest);
			if (testMdp) {
				testOk = true;
			} else {
				System.err.println("mot de passe incorect");
			}
			
		}else {
			System.err.println("problème de remplissage de champs");
		}
		return testOk;
	}
}
