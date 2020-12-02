package fr.appli.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.appli.encheres.bo.Utilisateur;
import fr.appli.encheres.dal.ConnectionProvider;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.dao.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	// --------REQUETES---------------
	// SELECTION DES UTILISATEURS DE DIFFERENTES FACON 
	
	public static final String SELECT_BY_PSEUDO = "SELECT * FROM dbo.UTILISATEURS WHERE pseudo = ?";
	public static final String SELECT_BY_NO_UTILISATEUR = "SELECT * FROM dbo.UTILISATEURS WHERE no_utilisateur = ?";
	public static final String SELECT_ALL = "SELECT * FROM dbo.UTILISATEURS";
	
	//INSERT -- UPDATE -- DELETE
	
	public static final String INSERT = "INSERT INTO dbo.UTILISATEURS(pseudo, nom, prenom, email, telephone, "
			+ "rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE = "UPDATE dbo.UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, "
			+ "code_postal=?, ville=?, mot_de_passe=?,credit=?, administrateur=?  WHERE no_utilisateur=?";
	//SI SUPPRESSION UTILISATEUR ALORS SES ARTICLES AUSSI
	public static final String DELETE =  "DELETE FROM dbo.UTILISATEURS WHERE no_utilisateur=?";
	 //+ "DELETE FROM dbo.ARTICLES_VENDUS WHERE no_utilisateur=?";
	
	// ---------GESTION DES SELECTIONS--------------
	//SELECTIONNER UN UTILISATEUR PAR SON PSEUDO
	@Override
	public Utilisateur SelectByPseudo(String pseudo) throws DALException {
		Utilisateur utilisateur = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			stmt.setString(1, pseudo);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				Boolean administrateur = false; 
				if (rs.getInt("administrateur")!= 0) {
					administrateur = true;
				}
				
				utilisateur = new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						administrateur
						);
			} else {
				System.out.println("utilisateur null");
			}
		} catch (Exception e) {
			throw new DALException ("Erreur dans la selection pseudo", e);
		}	finally {
			try {

				if (stmt != null)
					stmt.close();
				
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
				return utilisateur;		
	}
	
	//SELECTIONNER UN UTILISATEUR PAR SON NO_UTILISATEUR
	@Override
	public Utilisateur SelectByNoUtilisateur(int no_utilisateur) throws DALException {
			Utilisateur utilisateur = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(SELECT_BY_NO_UTILISATEUR);
				stmt.setInt(1, no_utilisateur);
				rs = stmt.executeQuery();
				
			if (rs.next()) {
				Boolean administrateur = false; 
				if (rs.getInt("administrateur")!= 0) {
					administrateur = true;
				}
				
				utilisateur = new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						administrateur
						);
			} else {
				System.out.println("utilisateur null");
			}
		} catch (Exception e) {
			throw new DALException ("Erreur dans la selection de l'utilisateur", e);
		}	finally {
			try {

				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		return utilisateur;	
	}
	
	//SELECTIONER TOUS LES UTILISATEURS 
	@Override
	public List<Utilisateur> SelectAll() throws DALException {
		List<Utilisateur> userList= new ArrayList<Utilisateur>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(SELECT_ALL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur")
						);
				
				userList.add(utilisateur);	
			}
		} catch (Exception e) {
			throw new DALException("erreur de selection", e);
		} finally {
			try {

				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}	
		return userList;
	}
	
	// --------- GESTION DE L'INSERTION---------------
	@Override
	public void Insert(Utilisateur utilisateur) throws DALException {
		PreparedStatement stmt = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.isAdministrateur());
			
			stmt.executeUpdate();
			
			
			ResultSet rsNoUtilisateur = stmt.getGeneratedKeys();
			if(rsNoUtilisateur.next()) {
				utilisateur.setNoUtilisateur(rsNoUtilisateur.getInt(1));
			}	
		} catch (Exception e) {
			throw new DALException("Erreur dans l'insertion",e);
		}finally {
			try {

				if (stmt != null)
					stmt.close();
				
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	}
	
	//------------ GESTION DES MISES A JOURS------------------
	public void update(Utilisateur utilisateur) throws DALException {
		
		PreparedStatement stmt = null;
		try  (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(UPDATE);
		
			
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.isAdministrateur());
			stmt.setInt(12, utilisateur.getNoUtilisateur());

			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new DALException("Probleme - modifierProfil - " + e.getMessage());
		} finally {
			try {

				if (stmt != null)
					stmt.close();
				
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	}

	// ---GESTION DE LA SUPPRESSION DE L'UTILISATEUR DANS LA BDD---
	@Override
	public void Delete(int no_utilisateur) throws DALException {
		PreparedStatement stmt = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(DELETE);
			stmt.setInt(1, no_utilisateur);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("la suppression a souleve une exception");
			throw new DALException("Erreur de suppression",e);
		} finally {
			try {

				if (stmt != null)
					stmt.close();
			
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	}
		
}
