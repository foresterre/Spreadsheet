package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the outcome of an or operation 
 * Arguments: formula, cell, letters
 * 
 * =OR(P1,P2)
 * P1 and P2 have to be logical values
 * Use a truth table to check OR
 * If P1 is TRUE and P2 is TRUE, Returns TRUE
 * If P1 is TRUE and P2 is FALSE, Returns TRUE
 * If P1 is FALSE and P2 is TRUE, Returns TRUE
 * If P1 is FALSE and P2 is FALSE, Returns FALSE
 * 
 * Can not use ranges
 * 
 * Can be nested
 * Example: =OR(TRUE,OR(TRUE,FALSE))
 * Will return TRUE
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Or
{
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*OR\\(\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z]+)\\s*,\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z]+)\\s*\\)\\s*");

	/**
	 * Evaluation of the Or formula
	 * 
	 * @param formula The formula to be parsed
	 * @param data The data of the sheet object
	 * @return Logical or, returns TRUE or FALSE
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 */
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException
	{
		String res = "NOT A LOGICAL VALUE";

		Matcher m = formulaPattern.matcher(formula);
		if (m.find())
		{
			String group1 = m.group(1);
			group1 = Parser.evaluate(group1, data);
			String group2 = m.group(2);
			group2 = Parser.evaluate(group2, data);

			if (group1.toUpperCase().equals("FALSE") && group2.toUpperCase().equals("FALSE"))
			{
				return "FALSE";
			}
			else if (group1.toUpperCase().equals("TRUE") || group2.toUpperCase().equals("TRUE"))
			{
				return "TRUE";
			}
		}
		return res;
	}
}
