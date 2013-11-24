/**
 * Exception thrown when a file is corrupt
 * 
 * @author m.olsthoorn
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
