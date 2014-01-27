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

	/**
	 * Serialize UID (Unused, but required)
	 */
	private static final long serialVersionUID = 74438857863696364L;
	
	/**
	 * Exception for a corrupt or otherwise unreadable file 
	 */
	public FileCorruptException() 
	{
		super();
	}
	
	/**
	 * Exception for a corrupt or otherwise unreadable file containing a message
	 * @param message Message of the exception
	 */
	public FileCorruptException(String message) 
	{
		super(message);
	}

	/**
	 * Exception for a corrupt or otherwise unreadable file containing a message and the cause
	 * @param message Message of the exception
	 * @param cause Cause of the exception
	 */
	public FileCorruptException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	
	/**
	 * Exception for a corrupt or otherwise unreadable file containing the cause
	 * @param cause Cause of the exception
	 */
	public FileCorruptException(Throwable cause) 
	{
		super(cause);
	}
}
