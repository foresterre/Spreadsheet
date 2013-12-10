package sheetproject.exception;

/**
 * Exception thrown when a number is out of bound
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class NumberOutOfBoundsException extends Exception {

	private static final long serialVersionUID = -1424706149160702191L;

	public NumberOutOfBoundsException() {
		super();
	}

	public NumberOutOfBoundsException(String message) {
		super(message);
	}

	public NumberOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumberOutOfBoundsException(Throwable cause) {
		super(cause);
	}
}
