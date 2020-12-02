package fr.appli.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.appli.encheres.bo.Retrait;
import fr.appli.encheres.dal.ConnectionProvider;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.dao.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO{
	//---------REQUETES--------------
	// SELECTION DES ARTICLES DE DIFFERENTES FACON 
	public static final String SELECT_BY_NO_ARTICLE = "SELECT * FROM dbo.RETRAITS WHERE no_article=?";
	public static final String SELECT_ALL = "SELECT * FROM dbo.RETRAITS";
	//INSERT -- UPDATE -- DELETE
	public static final String INSERT = "INSERT INTO dbo.RETRAITS ( no_article, rue, code_postal, ville) VALUES(?,?,?,?)";
	public static final String UPDATE = "UPDATE dbo.RETRAITS set rue=?, code_postal=?, ville=? WHERE no_article=?";
	public static final String DELETE = "DELETE FROM dbo.RETRAITS WHERE no_article=?"; 
	// ---------GESTION DES SELECTIONS--------------
	// SELECTION D'UN RETRAIT PAR SON ARTICLE
	@Override
	public Retrait selectByNoArticle(int no_article) throws DALException {
		Retrait retrait = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try(Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);
			stmt.setInt(1, no_article);
			rs = stmt.executeQuery();
			if(rs.next()) {
				retrait = new Retrait(
						no_article,
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville")
						);
			} else {
				System.out.println("retrait null");
			}
		} catch (Exception e) {
			throw new DALException("Erreur de selection d'article",e);
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
		return retrait;
	}
		
	//SELECTION GLOBALE
	@Override
	public List<Retrait> selectAll() throws DALException {
		List<Retrait> listRetrait = new ArrayList<Retrait>();
		Statement stmt = null;
		ResultSet rs = null;

		try(Connection cnx = ConnectionProvider.getConnection()) {
			 stmt = cnx.createStatement();
			 rs = stmt.executeQuery(SELECT_ALL);
			
			while(rs.next()) {	
				Retrait retrait = new Retrait(
					rs.getInt("no_article"),
					rs.getString("rue"),
					rs.getString("code_postal"),
					rs.getString("ville")
					);
				listRetrait.add(retrait);
			}
		} catch (Exception e) {
			throw new DALException("Erreur de selection globale",e);
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
		return listRetrait;
	}

	// --------- GESTION DE L'INSERTION---------------
	@Override
	public void insert(Retrait retrait) throws DALException {
		PreparedStatement stmt = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, retrait.getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCodePostal());
			stmt.setString(4, retrait.getVille());
			stmt.executeUpdate();							
		} catch (Exception e) {
			throw new DALException("Erreur d'insertion",e);
		} finally {
			try {

				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	}

	//------------ GESTION DES MISES A JOURS------------------
	@Override
	public void update(Retrait retrait) throws DALException {
		PreparedStatement stmt = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(UPDATE);
			stmt.setInt(1, retrait.getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCodePostal());
			stmt.setString(4, retrait.getVille());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Erreur de modification",e);
		}finally {
			try {

				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	}


	// ---GESTION DE LA SUPPRESSION DU RETRAIT DANS LA BDD--
	@Override
	public void delete(int no_article) throws DALException {
		PreparedStatement stmt = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(DELETE);
				stmt.setInt(1, no_article);
		} catch (Exception e) {
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
