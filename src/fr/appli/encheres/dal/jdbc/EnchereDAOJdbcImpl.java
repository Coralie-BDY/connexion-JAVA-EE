package fr.appli.encheres.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.appli.encheres.bo.Enchere;
import fr.appli.encheres.dal.ConnectionProvider;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.dao.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	//---------REQUETES--------------
	// SELECTION DES ARTICLES DE DIFFERENTES FACON 
	public static final String SELECT_BY_NO_UTILISATEUR = "SELECT * FROM dbo.ENCHERES WHERE no_utilisateur=?";
	public static final String SELECT_BY_NO_ARTICLE = "SELECT * FROM dbo.ENCHERES WHERE no_article=? ORDER BY montant_enchere";
	public static final String SELECT_ALL = "SELECT * FROM dbo.ENCHERES";
		
	//INSERT -- UPDATE -- DELETE
	public static final String INSERT = "INSERT INTO dbo.ENCHERES ( no_utilisateur, no_article, date_enchere, montant_enchere) "
			+ "VALUES(?,?,?,?)";
	// PAS UPDATE SUR L'ENCHERISSEUR (NO_UTILISATEUR)
	public static final String UPDATE = "UPDATE dbo.ENCHERES set no_article=?, date_enchere=?, montant_enchere=? WHERE no_article=?";
	public static final String DELETE = "DELETE FROM dbo.ENCHERES WHERE no_utilisateur=? AND no_article=?";
		
	// ---------GESTION DES SELECTIONS--------------
	// SELECTION D'UNE ENCHERE PAR SON ENCHERISSEUR
	@Override
		public Enchere selectByNoUtilisateur(int no_utilisateur) throws DALException {
			Enchere enchere = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(SELECT_BY_NO_UTILISATEUR);
				stmt.setInt(1, no_utilisateur);
				rs = stmt.executeQuery();

				if (rs.next()) {
					LocalDateTime date_enchere = LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(),rs.getTime("date_enchere").toLocalTime());							
					enchere = new Enchere( 
							no_utilisateur,
							rs.getInt("no_article"),
							rs.getInt("montant_enchere"),
							date_enchere
							);
				} else {
					System.out.println("utilisateur null");
				}	
			
			} catch (Exception e) {
				throw new DALException("Erreur de selection d'encherisseur",e);
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
			return enchere;
		}
	
	// SELECTION D'UNE ENCHERE PAR ARTICLE
		@Override
		public List<Enchere> selectByNoArticle(int no_article) throws DALException {
			Enchere enchere = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			List<Enchere> ListEnchere = new ArrayList<Enchere>();

			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);
				stmt.setInt(1, no_article);
				rs = stmt.executeQuery();

				if (rs.next()) {
					LocalDateTime date_enchere = LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(),rs.getTime("date_enchere").toLocalTime());							
					enchere = new Enchere( 
							rs.getInt("no_utilisateur"),
							no_article,
							rs.getInt("montant_enchere"),
							date_enchere
							);
					ListEnchere.add(enchere);
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
			return ListEnchere;
		}
		
		//SELECTION GLOBALE
		@Override
		public List<Enchere> selectAll() throws DALException {
			List<Enchere> listEnchere = new ArrayList<Enchere>();
			Statement stmt = null;
			ResultSet rs = null;
			try(Connection cnx = ConnectionProvider.getConnection()) {
				 stmt = cnx.createStatement();
				  rs = stmt.executeQuery(SELECT_ALL);
						
				while(rs.next()) {
							LocalDateTime date_enchere = LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(),rs.getTime("date_enchere").toLocalTime());
					Enchere enchere = new Enchere();
					enchere= new Enchere(
							rs.getInt("no_utilisateur"),
							rs.getInt("no_article"),
							rs.getInt("montant_enchere"),
							date_enchere
							);
					listEnchere.add(enchere);
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
			return listEnchere;
		}
		
		// --------- GESTION DE L'INSERTION---------------
		@Override
		public void insert(Enchere enchere) throws DALException {
			PreparedStatement stmt = null;
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, enchere.getEncherisseur());
				stmt.setInt(2, enchere.getNoArticle());
				stmt.setTimestamp(3,Timestamp.valueOf(enchere.getDateEnchere()));
				stmt.setInt(4, enchere.getMontantEnchere());
				stmt.executeUpdate();						
			} catch (Exception e) {
				throw new DALException("Erreur d'insertion",e);
			}
		}
		
		//------------ GESTION DES MISES A JOURS------------------
		@Override
		public void update(Enchere enchere) throws DALException {
			PreparedStatement stmt = null;
			try(Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(UPDATE);
				stmt.setInt(1, enchere.getEncherisseur());
				stmt.setInt(2, enchere.getNoArticle());
				stmt.setTimestamp(3,Timestamp.valueOf(enchere.getDateEnchere()));
				stmt.setInt(4, enchere.getMontantEnchere());
				stmt.executeUpdate();		
			} catch (Exception e) {
				throw new DALException("Erreur de modification",e);
			} finally {
				try {

					if (stmt != null)
						stmt.close();
				} catch (SQLException e) {
					throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
				}
			}
		}
		
		// ---GESTION DE LA SUPPRESSION DE L'ENCHERE DANS LA BDD--
		@Override
		public void delete(int no_utilisateur, int no_article) throws DALException {
			PreparedStatement stmt = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(DELETE);
				stmt.setInt(1, no_utilisateur);
				stmt.setInt(2,no_article);
				stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Erreur de suppression",e);
		}finally {
			try {

				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	}
		

}
