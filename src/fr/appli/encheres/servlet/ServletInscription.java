package fr.appli.encheres.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.appli.encheres.bll.BllException;
import fr.appli.encheres.bll.UtilisateurManager;
import fr.appli.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String err="";
		request.setAttribute("err",err);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String err="";
		request.setAttribute("err",err);
		
		//CREATION NOUVEL UTILISATEUR
		Utilisateur user = new Utilisateur( 
				request.getParameter("pseudo"),
				request.getParameter("nom"),
				request.getParameter("prenom"),
				request.getParameter("email"),
				request.getParameter("telephone"),
				request.getParameter("rue"),
				request.getParameter("code_postal"),
				request.getParameter("ville"),
				request.getParameter("mot_de_passe"),
				0,
				false);
		try {
			//INSERTION DE L'UTILISATEUR EN BDD
			if (UtilisateurManager.insertUser(user)) {
				user = UtilisateurManager.selectUser(user.getPseudo());
			
				HttpSession session = request.getSession();
				session.setAttribute("thisUser", user);
			
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
				rd.forward(request, response);
			} else {
			//AFFICHER ERREUR
			}
		
		} catch (SQLException e) {
			e.printStackTrace();			
		} catch (BllException e) {
			err = e.getMessage();
			request.setAttribute("err",err);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Inscription.jsp");
			rd.forward(request, response);
		}
	}

}
