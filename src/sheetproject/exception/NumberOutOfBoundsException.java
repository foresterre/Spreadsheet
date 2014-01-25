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
public class NumberOutOfBoundsException extends Exception 
{
	/**
	 * Serialize UID (Unused, but required)
	 */
	private static final long serialVersionUID = -1424706149160702191L;
	
	/**
	 * Exception for NumberOutOfBounds
	 */
	public NumberOutOfBoundsException() 
	{
		super();
	}
	
	/**
	 * Exception for NumberOutOfBounds containing a message
	 * @param message: message of the exeption
	 */
	public NumberOutOfBoundsException(String message) 
	{
		super(message);
	}
	
	/**
	 * Exception for NumberOutOfBounds containing a message and the cause
	 * @param message: message of the exception
	 * @param cause: cause of the exception
	 */
	public NumberOutOfBoundsException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	/**
	 * Exception for NumberOutOfBounds containing the cause
	 * @param cause: cause of the exception
	 */
	public NumberOutOfBoundsException(Throwable cause) 
	{
		super(cause);
	}
}
