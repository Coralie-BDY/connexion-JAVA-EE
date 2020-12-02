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

import fr.appli.encheres.bo.ArticleVendu;
import fr.appli.encheres.dal.ConnectionProvider;
import fr.appli.encheres.dal.DALException;
import fr.appli.encheres.dal.dao.ArticleVenduDAO;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	//---------REQUETES--------------
	// SELECTION DES ARTICLES DE DIFFERENTES FACON 
	public static final String SELECT_BY_NO_ARTICLE = "SELECT * FROM dbo.ARTICLES_VENDUS WHERE no_article=?";
	public static final String SELECT_BY_ARTICLE = "SELECT * FROM dbo.ARTICLES_VENDUS WHERE nom_acticle=?";
	public static final String SELECT_BY_NO_UTILISATEUR = "SELECT * FROM dbo.ARTICLES_VENDUS WHERE no_utilisateur=?";
	public static final String SELECT_ALL = "SELECT * FROM dbo.ARTICLES_VENDUS";
	//SELECTION DES ARTICLES EN COURS DE VENTE
	public static final String SELECT_EC_NO_ARTICLE = "SELECT * FROM dbo.ARTICLES_VENDUS WHERE etat_vente = 'EC' ORDER BY no_article DESC;";
	public static final String SELECT_EC_NO_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS " + 
			" WHERE etat_vente = 'EC' AND no_categorie = ? " + 
			"ORDER BY no_article DESC;";
	public static final String SELECT_EC_NOM_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE etat_vente = 'EC' AND nom_article LIKE ? " +
			"ORDER BY no_article DESC;";
	public static final String SELECT_EC_NOM_ARTICLE_NO_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE etat_vente = 'EC' AND nom_article"
			+ "LIKE ? AND no_categorie = ? ORDER BY no_article DESC; ";
	
	//INSERT -- UPDATE -- DELETE
	public static final String INSERT = "INSERT INTO dbo.ARTICLES_VENDUS(nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image) VALUES(?,?,?,?,?,?,?,?,?,?)";	
	// PAS UPDATE SUR LE VENDEUR (NO_UTILISATEUR)
	public static final String UPDATE = "UPDATE dbo.ARTICLES_VENDUS set nom_article=?, description=?, date_debut_encheres=?, "
			+ "date_fin_encheres=?, prix_initial=?, prix_vente=?, no_categorie=?, etat_vente=?,image=? WHERE no_article=?";
	//SI SUPPRESSION DE L'ARTICLE ALORS SUPPRESSION DANS LES AUTRES TABLES OU SON NUMERO APPARAIT
	public static final String DELETE = "DELETE FROM dbo.ARTICLES_VENDUS WHERE no_article=?; "
			+ "DELETE FROM dbo.ENCHERES WHERE no_article=?; "
			+ "DELETE FROM dbo.RETRAITS WHERE no_article=?; ";
	
	// ---------GESTION DES SELECTIONS--------------
	// SELECTION D'UN ARTICLE PAR SON NO_ARTICLE
	@Override
	public ArticleVendu selectByNoArticle(int no_article) throws DALException {
		ArticleVendu article = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);
			stmt.setInt(1, no_article);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				LocalDateTime date_debut_encheres = LocalDateTime.of(rs.getDate("date_debut_encheres").toLocalDate(),rs.getTime("date_debut_encheres").toLocalTime());
				LocalDateTime date_fin_encheres = LocalDateTime.of(rs.getDate("date_fin_encheres").toLocalDate(),rs.getTime("date_fin_encheres").toLocalTime());
				
				article = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						date_debut_encheres,
						date_fin_encheres,
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"),
						rs.getString("etat_vente"),
						rs.getString("image")
						);
			} else {
				System.out.println("article null");
			}
		} catch (Exception e) {
			throw new DALException("Erreur selection d'article",e);
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
		return article;
	}
	public ArticleVendu selectByArticle(String nom_article) throws DALException {
		ArticleVendu article = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(SELECT_BY_ARTICLE);
			stmt.setString(1, nom_article);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				LocalDateTime date_debut_encheres = LocalDateTime.of(rs.getDate("date_debut_encheres").toLocalDate(),rs.getTime("date_debut_encheres").toLocalTime());
				LocalDateTime date_fin_encheres = LocalDateTime.of(rs.getDate("date_fin_encheres").toLocalDate(),rs.getTime("date_fin_encheres").toLocalTime());
				
				article = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						date_debut_encheres,
						date_fin_encheres,
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"),
						rs.getString("etat_vente"),
						rs.getString("image")
						);
			} else {
				System.out.println("article null");
			}
		} catch (Exception e) {
			throw new DALException("Erreur selection d'article",e);
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
		return article;
	}
	
	//SELECTION D'ARTICLES PAR LA SELECTION DU VENDEUR (NO_UTILISATEUR)
	@Override
	public List<ArticleVendu> selectByVendeur(int no_utilisateur) throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(SELECT_BY_NO_UTILISATEUR);
			stmt.setInt(1, no_utilisateur);
			rs = stmt.executeQuery();
			
			while(rs.next()) {			
				LocalDateTime date_debut_encheres = LocalDateTime.of(rs.getDate("date_debut_encheres").toLocalDate(),rs.getTime("date_debut_encheres").toLocalTime());
				LocalDateTime date_fin_encheres = LocalDateTime.of(rs.getDate("date_fin_encheres").toLocalDate(),rs.getTime("date_fin_encheres").toLocalTime());
	
				ArticleVendu article = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						date_debut_encheres,
						date_fin_encheres,
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"),
						rs.getString("etat_vente"),
						rs.getString("image")
						);
				
				listeArticles.add(article);
			}
		} catch (Exception e) {
			throw new DALException("Erreur de selection du vendeur",e);
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
		return listeArticles;
	}
	
	//SELECTION GLOBALE
	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.createStatement();
				rs = stmt.executeQuery(SELECT_ALL);
			
			while(rs.next()) {			
				LocalDateTime date_debut_encheres = LocalDateTime.of(rs.getDate("date_debut_encheres").toLocalDate(),rs.getTime("date_debut_encheres").toLocalTime());
				LocalDateTime date_fin_encheres = LocalDateTime.of(rs.getDate("date_fin_encheres").toLocalDate(),rs.getTime("date_fin_encheres").toLocalTime());
	
				ArticleVendu article = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						date_debut_encheres,
						date_fin_encheres,
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"),
						rs.getString("etat_vente"),
						rs.getString("image")
						);
				
				listeArticles.add(article);
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
		return listeArticles;
	}
	
	//SELECTION D'ARTICLES EN COURS TRIE PAR LE NO_ARTICLE
	@Override
	public List<ArticleVendu> selectEcNoArticle() throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		Statement stmt = null;
		ResultSet rs = null;	
		try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.createStatement();
				rs = stmt.executeQuery(SELECT_EC_NO_ARTICLE);
			
			while(rs.next()) {			
				LocalDateTime date_debut_encheres = LocalDateTime.of(rs.getDate("date_debut_encheres").toLocalDate(),rs.getTime("date_debut_encheres").toLocalTime());
				LocalDateTime date_fin_encheres = LocalDateTime.of(rs.getDate("date_fin_encheres").toLocalDate(),rs.getTime("date_fin_encheres").toLocalTime());
	
				ArticleVendu article = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						date_debut_encheres,
						date_fin_encheres,
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"),
						rs.getString("etat_vente"),
						rs.getString("image")
						);
				
				listeArticles.add(article);
			}
		} catch (Exception e) {
			throw new DALException("Erreur de selection des articles en cours de vente par son numero",e);
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
		return listeArticles;
	}
	
	//SELECTION D'ARTICLES PAR LA SELECTION DU VENDEUR (NO_UTILISATEUR)
		@Override
		public List<ArticleVendu> selectEcNoCategorie(int no_categorie) throws DALException {
			List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(SELECT_EC_NO_CATEGORIE);
				stmt.setInt(1, no_categorie);
				rs = stmt.executeQuery();
				
				while(rs.next()) {			
					LocalDateTime date_debut_encheres = LocalDateTime.of(rs.getDate("date_debut_encheres").toLocalDate(),rs.getTime("date_debut_encheres").toLocalTime());
					LocalDateTime date_fin_encheres = LocalDateTime.of(rs.getDate("date_fin_encheres").toLocalDate(),rs.getTime("date_fin_encheres").toLocalTime());
		
					ArticleVendu article = new ArticleVendu(
							rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description"),
							date_debut_encheres,
							date_fin_encheres,
							rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),
							rs.getInt("no_utilisateur"),
							rs.getInt("no_categorie"),
							rs.getString("etat_vente"),
							rs.getString("image")
							);
					
					listeArticles.add(article);
				}
			} catch (Exception e) {
				throw new DALException("Erreur de selection des articles en cours de vente par sa categorie",e);
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
			return listeArticles;
		}
		
		//SELECTIONNER ARTICLES PAR LE NOM
		@Override
		public List<ArticleVendu> selectEcNomArticle(String nom_article) throws DALException {
			List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(SELECT_EC_NOM_ARTICLE);
				stmt.setString(1, nom_article);
				rs = stmt.executeQuery();
				
				while(rs.next()) {			
					LocalDateTime date_debut_encheres = LocalDateTime.of(rs.getDate("date_debut_encheres").toLocalDate(),rs.getTime("date_debut_encheres").toLocalTime());
					LocalDateTime date_fin_encheres = LocalDateTime.of(rs.getDate("date_fin_encheres").toLocalDate(),rs.getTime("date_fin_encheres").toLocalTime());
		
					ArticleVendu article = new ArticleVendu(
							rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description"),
							date_debut_encheres,
							date_fin_encheres,
							rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),
							rs.getInt("no_utilisateur"),
							rs.getInt("no_categorie"),
							rs.getString("etat_vente"),
							rs.getString("image")
							);
					
					listeArticles.add(article);
				}
			} catch (Exception e) {
				throw new DALException("Erreur de selection des articles en cours de vente par le nom",e);
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
			return listeArticles;
		}
		
		//SELECTIONNER ARTICLES PAR LE NOM ET LA CATEGORIE
		@Override
		public List<ArticleVendu> selectEcNomArticleNoCategorie(String nom_article, int no_categorie) throws DALException {
			List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
					
			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(SELECT_EC_NOM_ARTICLE_NO_CATEGORIE);
				stmt.setString(1, nom_article);
				stmt.setInt(1, no_categorie);
				rs = stmt.executeQuery();
						
				while(rs.next()) {			
					LocalDateTime date_debut_encheres = LocalDateTime.of(rs.getDate("date_debut_encheres").toLocalDate(),rs.getTime("date_debut_encheres").toLocalTime());
					LocalDateTime date_fin_encheres = LocalDateTime.of(rs.getDate("date_fin_encheres").toLocalDate(),rs.getTime("date_fin_encheres").toLocalTime());
				
					ArticleVendu article = new ArticleVendu(
							rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description"),
							date_debut_encheres,
							date_fin_encheres,
							rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),
							rs.getInt("no_utilisateur"),
							rs.getInt("no_categorie"),
							rs.getString("etat_vente"),
							rs.getString("image")
							);
							
					listeArticles.add(article);
				}
			} catch (Exception e) {
				throw new DALException("Erreur de selection des articles en cours de vente par le nom",e);
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
			return listeArticles;
		}
				
		
		// --------- GESTION DE L'INSERTION---------------
		@Override
		public void insert(ArticleVendu article) throws DALException {
			PreparedStatement stmt = null;
			
			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, article.getNomArticle());
				stmt.setString(2, article.getDescription());
				stmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
				stmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
				stmt.setInt(5, article.getPrixInitial());
				stmt.setInt(6, article.getPrixVente());
				stmt.setInt(7, article.getVendeur());
				stmt.setInt(8, article.getNoCategorie());
				stmt.setString(9, article.getEtatVente());
				stmt.setString(10, article.getImage());
				stmt.executeUpdate();	
		
				ResultSet rsNoArticle = stmt.getGeneratedKeys();
				if (rsNoArticle.next()) {
					article.setNoArticle(rsNoArticle.getInt(1));
				}
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
		public void update(ArticleVendu article) throws DALException {
			PreparedStatement stmt = null;
			
			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(UPDATE);
				stmt.setString(1, article.getNomArticle());
				stmt.setString(2, article.getDescription());
				stmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
				stmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
				stmt.setInt(5, article.getPrixInitial());
				stmt.setInt(6, article.getPrixVente());
				stmt.setInt(7, article.getVendeur());
				stmt.setInt(8, article.getNoCategorie());
				stmt.setString(9, article.getEtatVente());
				stmt.setString(10, article.getImage());
				stmt.executeUpdate();						
		
				ResultSet rsNoArticle = stmt.getGeneratedKeys();
				if (rsNoArticle.next()) {
					article.setNoArticle(rsNoArticle.getInt(1));
				}
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
		
		// ---GESTION DE LA SUPPRESSION DE L'ARTICLE DANS LA BDD---
		@Override
		public void delete(int no_article) throws DALException {
			PreparedStatement stmt = null;
			try (Connection cnx = ConnectionProvider.getConnection()) {
				stmt = cnx.prepareStatement(DELETE);
				stmt.setInt(1, no_article);
				stmt.setInt(2, no_article);
				stmt.setInt(3, no_article);
				stmt.executeQuery(); 
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
