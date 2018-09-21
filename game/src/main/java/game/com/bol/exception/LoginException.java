package game.com.bol.exception;

public class LoginException extends Exception{

	private final String message;

	public LoginException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
