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

import fr.appli.encheres.bll.UtilisateurManager;
import fr.appli.encheres.bo.Utilisateur;
import fr.appli.encheres.dal.DALException;

/**
 * Servlet implementation class ServletSuppressionCompte
 */
@WebServlet("/SuppressionCompte")
public class ServletSuppressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.getAttribute("thisUser");

		RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/jsp/Accueil.jsp" );
		rd.forward( request, response );
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utilisateur thisUser=(Utilisateur)session.getAttribute("thisUser");
	
		int user;
		
		user = thisUser.getNoUtilisateur();
		try {
			UtilisateurManager.deleteUser(user);
			request.getSession().invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			rd.forward(request, response);
			
		} catch (SQLException | DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
	
}
