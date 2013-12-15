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
public class IllegalFormulaException extends Exception {

	private static final long serialVersionUID = -2677876290138999187L;

	public IllegalFormulaException() {
		super();
	}

	public IllegalFormulaException(String message) {
		super(message);
	}

	public IllegalFormulaException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalFormulaException(Throwable cause) {
		super(cause);
	}
}
