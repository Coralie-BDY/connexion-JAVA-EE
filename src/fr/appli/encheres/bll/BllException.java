package fr.appli.encheres.bll;

public class BllException extends RuntimeException {

	private static final long serialVersionUID = 1L;
String message;
	
	public BllException (String message) {
		this.message=message;
		System.err.println("Exception BLL");
		System.err.println(message);
		printStackTrace();
	}
	
	public String getMessage() {
		return this.message;
	}
}
