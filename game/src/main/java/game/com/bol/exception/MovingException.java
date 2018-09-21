package game.com.bol.exception;

public class MovingException extends Exception {
	
	private final String message;

	public MovingException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


}
