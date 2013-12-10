package sheetproject.exception;

/**
 * Exception thrown when a object is null
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class NullObjectException extends Exception {

	private static final long serialVersionUID = 485047959240791249L;

	public NullObjectException() {
		super();
	}

	public NullObjectException(String message) {
		super(message);
	}

	public NullObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullObjectException(Throwable cause) {
		super(cause);
	}
}
