package sheetproject.exception;

/**
 * Exception thrown when a character is out of the set bounds
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class CharacterOutOfBoundsException extends Exception 
{
	/**
	 * Serialize UID (Unused, but required)
	 */
	private static final long serialVersionUID = 1749308240505726326L;
	
	/**
	 * Exception constructor for a character out of the set bounds
	 */
	public CharacterOutOfBoundsException() 
	{
		super();
	}
	
	/**
	 * Exception for a character out of the set bounds containing a message
	 * @param String Message of the exception
	 */
	public CharacterOutOfBoundsException(String message) 
	{
		super(message);
	}

	/**
	 * Exception for a character out of the set bounds containing a message and the cause
	 * @param String Message of the exception
	 * @param Throwable Cause of the exception
	 */
	public CharacterOutOfBoundsException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	/**
	 * Exception for a character out of the set bounds containing the cause
	 * @param Throwable Cause of the exception
	 */
	public CharacterOutOfBoundsException(Throwable cause) 
	{
		super(cause);
	}
}
