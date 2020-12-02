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
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RECUPERATION SESSION
		HttpSession session = request.getSession();
		session.getAttribute("thisUser");
				
		//REDIRECTION
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ModifierProfil.jsp");
		rd.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String err="";
		request.setAttribute("err",err);
		request.setCharacterEncoding("UTF-8");
		
		
		HttpSession session = request.getSession();
		Utilisateur thisUser=(Utilisateur)session.getAttribute("thisUser");
		
		thisUser.setPseudo(request.getParameter("pseudo"));
		thisUser.setNom(request.getParameter("nom"));
		thisUser.setPrenom(request.getParameter("prenom"));
		thisUser.setEmail(request.getParameter("email"));
		thisUser.setTelephone(request.getParameter("telephone"));
		thisUser.setRue(request.getParameter("rue"));
		thisUser.setVille(request.getParameter("ville"));
		thisUser.setMotDePasse(request.getParameter("mot_de_passe"));
		

		
		try {
			//MAJ UTILISATEUR DANS LA BDD
			UtilisateurManager.updateUser(thisUser);
		
			//REDIRECTION JSP PROFIL 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Profil.jsp");
			rd.forward(request, response);
		} catch ( SQLException | DALException e) {
			e.printStackTrace();			
		} 
	}

}
