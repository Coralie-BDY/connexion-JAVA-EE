package fr.appli.encheres.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.appli.encheres.bll.UtilisateurManager;
import fr.appli.encheres.bll.check.CheckUser;
import fr.appli.encheres.bo.Utilisateur;


/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String err = "";
		request.setAttribute("err", err);
		
		String beConnected = getCookieValue(request,"beConnected");
		 HttpSession session = request.getSession();
		if (beConnected!=null) {
			try {				
				session.setAttribute("thisUser",UtilisateurManager.selectUser(Integer.parseInt(beConnected)));
				} catch(SQLException e) {
					e.printStackTrace();
				}
			request.setAttribute("anUser", session.getAttribute("thisUser"));
			response.sendRedirect(request.getContextPath() + "/Accueil");
		     
		} else {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Connexion.jsp");
		rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("pseudo");
		String mdp = request.getParameter("mot_de_passe");
		Utilisateur thisUser = null;
		try {
			
			if(CheckUser.identificationOk(id,mdp)) {
				thisUser = UtilisateurManager.selectUser(id);
		        HttpSession session = request.getSession();
		        session.setAttribute("thisUser", thisUser);
				
		        //SI COCHER CASE SE SOUVENIR DE MOI
			    if ( request.getParameter( "remember" ) != null ) {
			    	String beConnected =String.valueOf(thisUser.getNoUtilisateur());
			    	System.out.println("beConnected: "+ beConnected);
			       //CREATION COOKIE + DANS REPONSE HTTP
			        setCookie(response, "beConnected", beConnected, 365*24*60*60 );
			    } else {
			        //SUPPRESSION DU COOKIE DU NAVIGATEUR
			        setCookie( response, "beConnected", "", 0 );
			    }
				request.setAttribute("thisUser", thisUser);
				response.sendRedirect(request.getContextPath() + "/Accueil");
			} else {
				request.setAttribute("err", "login ou mot de passe incorrect");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Connexion.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	//METHODE RECUPERANT LA VALEUR D'UN COOKIE DEPUIS REQUETE HTTP
	private static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( cookie != null && name.equals( cookie.getName() ) ) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


	//METHODE CREATION COOKIE ET AJOUT A REPONSE HTTP
	private static void setCookie( HttpServletResponse response, String name, String value, int maxAge ) {
        Cookie cookie = new Cookie( name, value );
        cookie.setMaxAge( maxAge );
        response.addCookie( cookie );
	}

}
