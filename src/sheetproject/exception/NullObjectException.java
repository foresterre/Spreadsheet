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
public class NullObjectException extends Exception 
{
	/**
	 * Serialize UID (Unused, but required)
	 */
	private static final long serialVersionUID = 485047959240791249L;

	/**
	 * Exception for a null object 
	 */
	public NullObjectException() 
	{
		super();
	}
	
	/**
	 * Exception for a null object containing a message
	 * @param message: message of the exception
	 */
	public NullObjectException(String message) 
	{
		super(message);
	}

	/**
	 * Exception for a null object containing a message and the cause
	 * @param message: message of the exception
	 * @param cause: cause of the exception
	 */
	public NullObjectException(String message, Throwable cause) 
	{
		super(message, cause);
	}
	
	/**
	 * Exception for a null object containing the cause
	 * @param cause: cause of the exception
	 */
	public NullObjectException(Throwable cause) 
	{
		super(cause);
	}
}
