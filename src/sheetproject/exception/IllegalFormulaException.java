package sheetproject.exception;

/**
 * Exception thrown when a formula is illegal
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class IllegalFormulaException extends Exception 
{

	/**
	 * Serialize UID (Unused, but required)
	 */
	private static final long serialVersionUID = -2677876290138999187L;
	
	/**
	 * Exception for an illegal formula
	 */
	public IllegalFormulaException() 
	{
		super();
	}
	
	/**
	 * Exception for an illegal formula containing a message
	 * @param message: message of the exception
	 */
	public IllegalFormulaException(String message) 
	{
		super(message);
	}

	/**
	 * Exception for an illegal formula containing a message and the cause
	 * @param message: message of the exception
	 * @param cause: cause of the exception
	 */
	public IllegalFormulaException(String message, Throwable cause) 
	{
		super(message, cause);
	}
	
	/**
	 * Exception for an illegal formula containing the cause
	 * @param cause: cause of the exception
	 */
	public IllegalFormulaException(Throwable cause) 
	{
		super(cause);
	}
}
