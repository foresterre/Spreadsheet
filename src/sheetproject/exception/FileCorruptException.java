package sheetproject.exception;

/**
 * Exception thrown when a file is corrupt
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class FileCorruptException extends Exception {

	private static final long serialVersionUID = 74438857863696364L;

	public FileCorruptException() {
		super();
	}

	public FileCorruptException(String message) {
		super(message);
	}

	public FileCorruptException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileCorruptException(Throwable cause) {
		super(cause);
	}
}
