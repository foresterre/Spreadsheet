package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the power of two values. 
 * Arguments: formula, number, cell
 * 
 * =POWER(PARAM1,PARAM2) 
 * returns PARAM1 to the PARAM2-th power 
 * Any parameter has to be a number
 * 
 * Can not be a range
 * 
 * Can be nested.
 * Example: =POWER(PARAM1,POWER(PARAM2,PARAM3))
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Power
{
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*POWER\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");

	/**
	 * Evaluation of the Power formula
	 * @param formula: the formula to be parsed
	 * @param data: the data of the sheet object
	 * @return power of value A to value B
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
			String group2 = m.group(2);
			group2 = Parser.evaluate(group2, data);

			double temp1 = 0;
			try
			{
				temp1 = Double.parseDouble(group1);
			}
			catch (Exception e)
			{
				// This catch statement is to catch exceptions that are not important for the executing of our application
			}

			double temp2 = 0;
			try
			{
				temp2 = Double.parseDouble(group2);
			}
			catch (Exception e)
			{
				// This catch statement is to catch exceptions that are not important for the executing of our application
			}

			res = Double.toString(Math.pow(temp1, temp2));
		}
		return res;
	}
}
