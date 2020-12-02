package fr.appli.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet("/Deconnexion")
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession();
		try {      
		       session.removeAttribute("logonSessData");
		       session.invalidate();   
		       
		       setCookie(response,"beConnected","",0);
		       response.sendRedirect(request.getContextPath());           
		}
		catch (Exception sql ){
		       System.out.println("error UserValidateServlet message : " + sql.getMessage());
		       System.out.println("error UserValidateServlet exception : " + sql);
		}	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static void setCookie( HttpServletResponse response, String name, String value, int maxAge ) {
	    Cookie cookie = new Cookie( name, value );
	    cookie.setMaxAge( maxAge );
	    response.addCookie( cookie );
	}

}
