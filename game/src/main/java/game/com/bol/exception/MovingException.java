package game.com.bol.exception;

public class MovingException extends Exception {
	
	private final String message;

	public MovingException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


}
