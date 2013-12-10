package sheetproject.exception;

/**
 * Exception thrown when a character is out of bound
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class CharacterOutOfBoundsException extends Exception {

	private static final long serialVersionUID = 1749308240505726326L;

	public CharacterOutOfBoundsException() {
		super();
	}

	public CharacterOutOfBoundsException(String message) {
		super(message);
	}

	public CharacterOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CharacterOutOfBoundsException(Throwable cause) {
		super(cause);
	}
}
