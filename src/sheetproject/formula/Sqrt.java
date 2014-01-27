package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the square root of a value. 
 * Arguments: formula, cell, value
 * 
 * =SQRT(PARAM)
 * PARAM has to be a number
 * returns the square root of the PARAM
 * 
 * Can not use ranges
 * Can be nested
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class Sqrt
{
	
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*SQRT\\(\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");
	
	/**
	 * Evaluation of the Sqrt formula
	 * @param formula: the formula to be parsed
	 * @param data: the data of the sheet object
	 * @return returns the squareroot of the parameter
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 */
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException
	{
		String res = "";

		Matcher m = formulaPattern.matcher(formula);
		if (m.find())
		{
			String group1 = m.group(1);
			group1 = Parser.evaluate(group1, data);

			double temp = 0;
			try
			{
				temp += Double.parseDouble(group1);
			}
			catch (Exception e)
			{
				// This catch statement is to catch exceptions that are not important for the executing of our application
			}

			return Double.toString(Math.sqrt(temp));
		}
		return res;
	}
}
