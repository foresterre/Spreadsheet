package sheetproject.formula;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the value rounded up. 
 * Arguments: {formula, cell, value}, num_digits
 * 
 * =ROUNDUP(PARAM, DIGITS)
 * Rounds up a number (Here PARAM)
 * PARAM and DIGITS have to be numbers
 * 
 * Can not use ranges
 * Can use nesting
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Roundup
{
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*ROUNDUP\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");
	
	/**
	 * Evaluation of the Roundup formula
	 * 
	 * @param formula The formula to be parsed
	 * @param data The data of the sheet object
	 * @return The rounded up value
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

			BigDecimal bd = new BigDecimal(temp1);
			bd = bd.setScale((int) temp2, BigDecimal.ROUND_UP);
			return Double.toString(bd.doubleValue());
		}
		return res;
	}
}
