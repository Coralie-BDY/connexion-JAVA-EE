package fr.appli.encheres.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.appli.encheres.bll.ArticleVenduManager;
import fr.appli.encheres.bll.BllException;
import fr.appli.encheres.bll.RetraitManager;
import fr.appli.encheres.bo.ArticleVendu;
import fr.appli.encheres.bo.Retrait;
import fr.appli.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletCreationArticle
 */
@WebServlet("/Vente")
public class ServletCreationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String err = null;
		if(request.getAttribute("err") == null) {
				 err = "";
				 request.setAttribute("err",err);
		};
		
		//RECUPERATION DATE ACTUELLE POUR TRANSMISSION A LA JSP POUR PREREMPLISSAGE
		LocalDate localDate = LocalDate.now();
		request.setAttribute("date", localDate);
		
		//RECUPERATION DE H/MIN POUR TRANSMISSION A LA JSP POUR PREREMPLISSAGE.
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		
		// AJOUT DE TEMPS POUR FAIRE L'ANNONCE. 
		if (minute>=50) {
			minute = minute -50;
		    hour = hour + 1;
		} else if (minute<50 ) {
			minute = minute +10;
		}
		if (hour < 10 && minute < 10) {
			request.setAttribute("time", ("0" + hour + ":0" + minute));
		} else if (hour < 10 && minute >= 10 ) {
			request.setAttribute("time", ("0" + hour + ":" + minute));
		} else if (hour >= 10 && minute < 10) {
			request.setAttribute("time", (hour + ":0" + minute));
		} else if (hour >= 10 && minute >= 10) {
			request.setAttribute("time", (hour + ":" + minute));
		}
		        
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/VenteArticle.jsp");
		rd.forward(request, response);
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String err="";
		request.setAttribute("err",err);
		
		//SESSION UTILISATEUR
		HttpSession session = request.getSession();
		Utilisateur thisUser = (Utilisateur)session.getAttribute("thisUser");
        
		//RECUPERATION DATE ET HEURE DU DEBUT
		
		LocalDateTime date_debut_encheres = null;
		String dateDebut = null;
		String heureDebut = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		try {
			 dateDebut = request.getParameter("dateDebut");
			 heureDebut = request.getParameter("heuredebut");
	
			//CONVERTION EN UN SEUL PARAMETRE
//		
			date_debut_encheres = LocalDateTime.parse(dateDebut+"T"+heureDebut, formatter);
		} catch (DateTimeException e) {
			e.printStackTrace();
		}
		
//		if (dateDebut.equals("") || heureDebut.equals("")) {
//			err = "Les champs de date et d'heure de début d'enchère sont obligatoires";
//			request.setAttribute("err",err);
//			doGet(request, response);
//		} else {
			//AJOUT DUREE POUR DETERMINER FIN DE LA MISE EN VENTE
			//RECUPERATION DES DUREES PROPOSEES
			String duree = request.getParameter("duree");
			int tps = Integer.parseInt(duree);
			LocalDateTime date_fin_encheres = date_debut_encheres.plusDays(tps);
			
			//PRIX INITIAL EN INTEGER TO STRING
			int prix_initial = Integer.parseInt(request.getParameter("prix_initial"));
			
			//NO CATEGORIE EN INTEGER TO STRING
			int no_categorie =Integer.parseInt(request.getParameter("no_categorie"));
			
			//CREATION DE L'ARTICLE
			ArticleVendu article = new ArticleVendu(
				request.getParameter("nom_article"),
				request.getParameter("description"),
				date_debut_encheres,
				date_fin_encheres,
				prix_initial,
				prix_initial,//PRIX DE VENTE A 0 CAR PAS ENCORE VENDU
				(int)thisUser.getNoUtilisateur(),
				no_categorie,
				"CR",
				"image" ); 
			
				try {
					if (ArticleVenduManager.insertArticle(article) ) {
						Retrait retrait = new Retrait(
								article.getNoArticle(),
								request.getParameter("rue"),
								request.getParameter("code_postal"),
								request.getParameter("ville"));
						
						if ( RetraitManager.createRetrait(retrait)) {
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Historique.jsp" );
							rd.forward(request, response);
						}
					}
				}  catch (SQLException e) {
					e.printStackTrace();
					System.out.println("SQL EXCEPTION");
				}catch (BllException e) {
					System.out.println("erreur article");
					request.setAttribute("err",e.getMessage());
					
					doGet(request, response);
				}
			}
		
	}

//}
