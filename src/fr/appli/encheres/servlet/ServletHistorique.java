package fr.appli.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.appli.encheres.bll.ArticleVenduManager;
import fr.appli.encheres.bo.ArticleVendu;
import fr.appli.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletHistorique
 */
@WebServlet("/Historique")
public class ServletHistorique extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String thisUser = null;
		HttpSession session = request.getSession();
		session.setAttribute("thisUser", thisUser);
		
		List<ArticleVendu> articleList = new ArrayList<ArticleVendu>();
		
		
		articleList = ArticleVenduManager.selectVendeur(Integer.parseInt(thisUser));
		
		request.setAttribute("articleList", articleList.add((ArticleVendu) articleList));
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Historique.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
