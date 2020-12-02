package fr.appli.encheres.dal;

public class DALException extends Exception {
	

	/**
	 * ID par defaut
	 */
	private static final long serialVersionUID = 1L;

	//constructeurs
	public DALException() {
		super();		
	}
	
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message, Throwable exception) {
		super(message, exception);
	}
	
	//methodes
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("couche DAL - ");
		sb.append(super.getMessage());
		return sb.toString();
	}
}
